package ru.alfa.finditems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.alfa.finditems.models.Box;
import ru.alfa.finditems.models.Item;
import ru.alfa.finditems.repositories.BoxRepository;
import ru.alfa.finditems.repositories.ItemRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService implements ApplicationRunner {
    private final ItemRepository itemRepo;
    private final BoxRepository boxRepo;

    @Autowired
    public ItemService(ItemRepository itemRepo, BoxRepository boxRepo) {
        this.itemRepo = itemRepo;
        this.boxRepo = boxRepo;
    }

    public void fromXmlToDatabase(String link) throws ParserConfigurationException,
            IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Resource xmlResource = new DefaultResourceLoader().getResource(link);
        Document xmlDB = builder.parse(xmlResource.getFile());
        xmlDB.normalizeDocument();
        Element rootElem = xmlDB.getDocumentElement();
        fillDbWithDataFromXml(rootElem);
    }

    //GET PARENT NODE method
    private void fillDbWithDataFromXml(Element root) {
        String parentId = root.getAttribute("id");
        NodeList childrenList = root.getChildNodes();
        for (int i = 0; i < childrenList.getLength(); i++) {
            if (childrenList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element) childrenList.item(i);
                Integer id = Integer.parseInt(child.getAttribute("id"));
                String color = child.getAttribute("color");
                switch (child.getTagName()) {
                    case ("Item"):
                        if (parentId.isBlank() && color.isBlank()) {
                            Item item = new Item(id);
                            itemRepo.save(item);
                        } else if (parentId.isBlank()) {
                            Item item = new Item(id, color);
                            itemRepo.save(item);
                        } else if (color.isBlank()) {
                            Integer parId = Integer.parseInt(parentId);
                            Item item = new Item(id, boxRepo.getById(parId));
                            itemRepo.save(item);
                        } else {
                            Integer parId = Integer.parseInt(parentId);
                            Item item = new Item(id, boxRepo.getById(parId), color);
                            itemRepo.save(item);
                        }
                        break;
                    case ("Box"):
                        if (parentId.isBlank()) {
                            Box box = new Box(id);
                            boxRepo.save(box);
                        } else {
                            Integer parId = Integer.parseInt(parentId);
                            Box box = new Box(id, parId);
                            boxRepo.save(box);
                        }
                        if (child.hasChildNodes()) fillDbWithDataFromXml(child);
                }
            }
        }
    }


    public Integer[] getItemIdsArray(Integer containedIn, String color) {
        List<Integer> items = new ArrayList<>
                (itemRepo.findRecursivelyByBox_IdAndColor(containedIn, color));
        Integer[] itemIds = new Integer[items.size()];
        return items.toArray(itemIds);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String link = args.getSourceArgs()[0];
        fromXmlToDatabase(link);
    }
}
