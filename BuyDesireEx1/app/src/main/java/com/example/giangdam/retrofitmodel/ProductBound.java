
package com.example.giangdam.retrofitmodel;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;


public class ProductBound {

    @Expose
    private List<Desire> Desires = new ArrayList<Desire>();
    @Expose
    private Object DesiresSample;
    @Expose
    private Integer TotalDesiresCount;
    @Expose
    private Integer TotalDesiresNotInDefaultListCount;
    @Expose
    private Object SampleUsers;
    @Expose
    private String TargetDevice;
    @Expose
    private Boolean Success;
    @Expose
    private Boolean AppVersionIsNoLongerSupported;
    @Expose
    private Boolean AppVersionIsOutOfDate;
    @Expose
    private Object Message;
    @Expose
    private Integer DataVersion;

    /**
     *
     * @return
     *     The Desires
     */
    public List<Desire> getDesires() {
        return Desires;
    }

    /**
     *
     * @param Desires
     *     The Desires
     */
    public void setDesires(List<Desire> Desires) {
        this.Desires = Desires;
    }

    /**
     *
     * @return
     *     The DesiresSample
     */
    public Object getDesiresSample() {
        return DesiresSample;
    }

    /**
     *
     * @param DesiresSample
     *     The DesiresSample
     */
    public void setDesiresSample(Object DesiresSample) {
        this.DesiresSample = DesiresSample;
    }

    /**
     *
     * @return
     *     The TotalDesiresCount
     */
    public Integer getTotalDesiresCount() {
        return TotalDesiresCount;
    }

    /**
     *
     * @param TotalDesiresCount
     *     The TotalDesiresCount
     */
    public void setTotalDesiresCount(Integer TotalDesiresCount) {
        this.TotalDesiresCount = TotalDesiresCount;
    }

    /**
     *
     * @return
     *     The TotalDesiresNotInDefaultListCount
     */
    public Integer getTotalDesiresNotInDefaultListCount() {
        return TotalDesiresNotInDefaultListCount;
    }

    /**
     *
     * @param TotalDesiresNotInDefaultListCount
     *     The TotalDesiresNotInDefaultListCount
     */
    public void setTotalDesiresNotInDefaultListCount(Integer TotalDesiresNotInDefaultListCount) {
        this.TotalDesiresNotInDefaultListCount = TotalDesiresNotInDefaultListCount;
    }

    /**
     *
     * @return
     *     The SampleUsers
     */
    public Object getSampleUsers() {
        return SampleUsers;
    }

    /**
     *
     * @param SampleUsers
     *     The SampleUsers
     */
    public void setSampleUsers(Object SampleUsers) {
        this.SampleUsers = SampleUsers;
    }

    /**
     *
     * @return
     *     The TargetDevice
     */
    public String getTargetDevice() {
        return TargetDevice;
    }

    /**
     *
     * @param TargetDevice
     *     The TargetDevice
     */
    public void setTargetDevice(String TargetDevice) {
        this.TargetDevice = TargetDevice;
    }

    /**
     *
     * @return
     *     The Success
     */
    public Boolean getSuccess() {
        return Success;
    }

    /**
     *
     * @param Success
     *     The Success
     */
    public void setSuccess(Boolean Success) {
        this.Success = Success;
    }

    /**
     *
     * @return
     *     The AppVersionIsNoLongerSupported
     */
    public Boolean getAppVersionIsNoLongerSupported() {
        return AppVersionIsNoLongerSupported;
    }

    /**
     *
     * @param AppVersionIsNoLongerSupported
     *     The AppVersionIsNoLongerSupported
     */
    public void setAppVersionIsNoLongerSupported(Boolean AppVersionIsNoLongerSupported) {
        this.AppVersionIsNoLongerSupported = AppVersionIsNoLongerSupported;
    }

    /**
     *
     * @return
     *     The AppVersionIsOutOfDate
     */
    public Boolean getAppVersionIsOutOfDate() {
        return AppVersionIsOutOfDate;
    }

    /**
     *
     * @param AppVersionIsOutOfDate
     *     The AppVersionIsOutOfDate
     */
    public void setAppVersionIsOutOfDate(Boolean AppVersionIsOutOfDate) {
        this.AppVersionIsOutOfDate = AppVersionIsOutOfDate;
    }

    /**
     *
     * @return
     *     The Message
     */
    public Object getMessage() {
        return Message;
    }

    /**
     *
     * @param Message
     *     The Message
     */
    public void setMessage(Object Message) {
        this.Message = Message;
    }

    /**
     *
     * @return
     *     The DataVersion
     */
    public Integer getDataVersion() {
        return DataVersion;
    }

    /**
     *
     * @param DataVersion
     *     The DataVersion
     */
    public void setDataVersion(Integer DataVersion) {
        this.DataVersion = DataVersion;
    }

}
