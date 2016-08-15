package com.myapp;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by JohnC on 2016-08-10.
 */
public class Program
{
    public static void main(String[] args) throws IOException
    {
        String path = readConsoleString("请输入目录地址：");
        /*String path = "E:\\temp\\20160725";*/
        if (path != null)
        {
            Files.walk(Paths.get(path)).forEach(filePath ->
            {
                if(filePath.toString().endsWith(".xml"))
                {
                    try
                    {
                        readXmlFile(filePath.toString());
                    }
                    catch (ParserConfigurationException e)
                    {
                        e.printStackTrace();
                    }
                    catch (SAXException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        
        
    }
    public static void readXmlFile(String filePath) throws ParserConfigurationException, SAXException, IOException
    {
        File file = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document document = dBuilder.parse(file);
        document.getDocumentElement().normalize();
        Element rootElement = document.getDocumentElement();
        
        String host = getElementTextContent(rootElement, "host", 0);
        System.out.println("host:" + host);
        String updatetime = getElementTextContent(rootElement, "updatetime", 0);
        System.out.println("updatetime:" + updatetime);
        String countryid = getElementTextContent(rootElement, "countryid", 0);
        System.out.println("countryid:" + countryid);
        String topicid = getElementTextContent(rootElement, "topicid", 0);
        System.out.println("topicid:" + topicid);
        String heat = getElementTextContent(rootElement, "heat", 0);
        System.out.println("heat:" + heat);
        String revertcount = getElementTextContent(rootElement, "revertcount", 0);
        System.out.println("revertcount:" + revertcount);
        String taskurl = getElementTextContent(rootElement, "taskurl", 0);
        System.out.println("taskurl:" + taskurl);
        String topictype = getElementTextContent(rootElement, "topictype", 0);
        System.out.println("topictype:" + topictype);
        String url = getElementTextContent(rootElement, "url", 0);
        System.out.println("url:" + url);
        String createtime = getElementTextContent(rootElement, "createtime", 0);
        System.out.println("createtime:" + createtime);
        String projectid = getElementTextContent(rootElement, "projectid", 0);
        System.out.println("projectid:" + projectid);
        String areaid = getElementTextContent(rootElement, "areaid", 0);
        System.out.println("areaid:" + areaid);
        String sitename = getElementTextContent(rootElement, "sitename", 0);
        System.out.println("sitename:" + sitename);
        String ip = getElementTextContent(rootElement, "ip", 0);
        System.out.println("ip:" + ip);
        String domaintype = getElementTextContent(rootElement, "domaintype", 0);
        System.out.println("domaintype:" + domaintype);
        String title = getElementTextContent(rootElement, "title", 0);
        System.out.println("title:" + title);
        String forumname = getElementTextContent(rootElement, "forumname", 0);
        System.out.println("forumname:" + forumname);
        String imagepath = getElementTextContent(rootElement, "imagepath", 0);
        System.out.println("imagepath:" + imagepath);
        String domain = getElementTextContent(rootElement, "domain", 0);
        System.out.println("domain:" + domain);
        String datasourcetype = getElementTextContent(rootElement, "datasourcetype", 0);
        System.out.println("datasourcetype:" + datasourcetype);
        String charset = getElementTextContent(rootElement, "charset", 0);
        System.out.println("charset:" + charset);
        NodeList articles = rootElement.getElementsByTagName("article");
        System.out.println(StringUtils.repeat("-", 50));
        SqlSession session = MyBatisUtil.getFactory().openSession();
        try
        {
            FenghuoMapper mapper = session.getMapper(FenghuoMapper.class);
            int count = mapper.countByfilePath(FilenameUtils.getName(filePath));
            if(count == 0)
            {
                BasicInfo info = new BasicInfo();
                info.setAreaid(areaid);
                info.setCharset(charset);
                info.setCountryid(countryid);
                info.setCreatetime(createtime);
                info.setDatasourcetype(datasourcetype);
                info.setDomain(domain);
                info.setDomaintype(domaintype);
                info.setForumname(forumname);
                info.setHeat(heat);
                info.setHost(host);
                info.setImagepath(imagepath);
                info.setIp(ip);
                info.setProjectid(projectid);
                info.setRevertcount(revertcount);
                info.setSitename(sitename);
                info.setTaskurl(taskurl);
                info.setTitle(title);
                info.setTopicid(topicid);
                info.setTopictype(topictype);
                info.setUpdatetime(updatetime);
                info.setFilePath(FilenameUtils.getName(filePath));
                mapper.insertBasicInfo(info);
                long basicInfo_id = info.getId();
                session.commit();
                if(articles != null && articles.getLength() > 0)
                {
                    for(int i = 0;i < articles.getLength();i++)
                    {
                        Element element = (Element)articles.item(i);
                        String nickname = getElementTextContent(element, "nickname", 0);
                        String postip = getElementTextContent(element, "postip", 0);
                        String postfloor = getElementTextContent(element, "postfloor", 0);
                        String posttime = getElementTextContent(element, "posttime", 0);
                        String location = getElementTextContent(element, "location", 0);
                        String userid = getElementTextContent(element, "userid", 0);
                        String summary = getElementTextContent(element, "summary", 0);
                        String source = getElementTextContent(element, "source", 0);
                        String postdata = getElementTextContent(element, "postdata", 0);
                        String imgurl = getElementTextContent(element, "imgurl", 0);
                        Article article = new Article();
                        article.setBasicinfo_id(basicInfo_id);
                        article.setImgurl(imgurl);
                        article.setLocation(location);
                        article.setNickname(nickname);
                        article.setPostdata(postdata);
                        article.setPostfloor(postfloor);
                        article.setPostip(postip);
                        article.setPosttime(posttime);
                        article.setSource(source);
                        article.setSummary(summary);
                        article.setUserid(userid);
                        mapper.insertArticle(article);
                        session.commit();
                        System.out.printf("nickname:%s,"
                                        + "postip:%s,"
                                        + "postfloor:%s,"
                                        + "posttime:%s,"
                                        + "location:%s,"
                                        + "userid:%s,"
                                        + "summary:%s,"
                                        + "source:%s,"
                                        + "postdata:%s,"
                                        + "imgurl:%s, \n",
                                nickname,
                                postip,
                                postfloor,
                                posttime,
                                location,
                                userid,
                                summary,
                                source,
                                postdata,
                                imgurl);
                    }
                }
                System.out.println("提取 " + filePath + " 文件成功");
            }
            else
            {
                System.out.println(filePath + " 文件已提取");
            }
            file.delete();
            
        }
        finally
        {
            session.close();
        }
    }
    public static String getElementTextContent(Element element,String tagname,int index)
    {
        NodeList elements = element.getElementsByTagName(tagname);
        if(elements != null && elements.item(index) != null)
        {
            return elements.item(index).getTextContent();
        }
        else
        {
            return null;
        }
        
    }
    public static String readConsoleString(String tip)
    {
        Console console = System.console();
        if (console == null)
        {
            throw new IllegalStateException("不能使用控制台");
        }
        return console.readLine(tip);
    }
}
