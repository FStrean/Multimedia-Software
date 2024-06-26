package ru.app.project.config.cards;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import ru.app.project.config.AppProperties;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VideoCInfoCfg {
    @XmlElement(name = "hHeader")
    private String hHeader;
    @XmlElement(name = "video")
    private List<String> videos;
    @XmlElement(name = "button1")
    private String button1;
    @XmlElement(name = "button2")
    private String button2;
    @XmlElement(name = "fHeader")
    private String fHeader;

    public VideoCInfoCfg() {
        this.hHeader = "";
        this.videos = new ArrayList<>();
        for(int i = 0; i < AppProperties.getNumOfVidInVideo(); i++) {
            this.videos.add("");
        }
        this.button1 = "";
        this.button2 = "";
        this.fHeader = "";
    }

    public String getHHeader() {
        return hHeader;
    }

    public List<String> getVideos() {
        return videos;
    }

    public String getButton1() {
        return button1;
    }

    public String getButton2() {
        return button2;
    }

    public String getFHeader() {
        return fHeader;
    }

    public void setHHeader(String hHeader) {
        this.hHeader = hHeader;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public void setButton2(String button2) {
        this.button2 = button2;
    }

    public void setButton1(String button1) {
        this.button1 = button1;
    }

    public void setFHeader(String fHeader) {
        this.fHeader = fHeader;
    }
}
