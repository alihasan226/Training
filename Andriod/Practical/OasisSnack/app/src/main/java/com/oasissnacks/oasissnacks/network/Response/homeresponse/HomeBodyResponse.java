package com.oasissnacks.oasissnacks.network.Response.homeresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeBodyResponse {
    @SerializedName("section-1")
    @Expose
    private Section1Response section1;
    @SerializedName("section-2")
    @Expose
    private String section2;
    @SerializedName("section-3")
    @Expose
    private Section3Response section3;
    @SerializedName("section-4")
    @Expose
    private String section4;
    @SerializedName("section-5")
    @Expose
    private Section5Response section5;

    public Section1Response getSection1() {
        return section1;
    }

    public void setSection1(Section1Response section1) {
        this.section1 = section1;
    }

    public String getSection2() {
        return section2;
    }

    public void setSection2(String section2) {
        this.section2 = section2;
    }

    public Section3Response getSection3() {
        return section3;
    }

    public void setSection3(Section3Response section3) {
        this.section3 = section3;
    }

    public String getSection4() {
        return section4;
    }

    public void setSection4(String section4) {
        this.section4 = section4;
    }

    public Section5Response getSection5() {
        return section5;
    }

    public void setSection5(Section5Response section5) {
        this.section5 = section5;
    }
}
