
package com.example.company.onetrak.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fit {

    @SerializedName("aerobic")
    private Integer aerobic;

    @SerializedName("date")
    private Long date;

    @SerializedName("run")
    private Integer run;

    @SerializedName("walk")
    private Integer walk;

    public Integer getAerobic() {
        return aerobic;
    }

    public void setAerobic(Integer aerobic) {
        this.aerobic = aerobic;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
        this.run = run;
    }

    public Integer getWalk() {
        return walk;
    }

    public void setWalk(Integer walk) {
        this.walk = walk;
    }

}
