package org.example.实际项目;

import java.math.BigDecimal;

class ReportWaferIvHour {
    private BigDecimal etaMin;
    private BigDecimal etaDownQuar;
    private BigDecimal etaMid;
    private BigDecimal etaUpQuar;
    private BigDecimal etaMax;

    // 构造函数
    public ReportWaferIvHour(BigDecimal etaMin, BigDecimal etaDownQuar, BigDecimal etaMid, BigDecimal etaUpQuar, BigDecimal etaMax) {
        this.etaMin = etaMin;
        this.etaDownQuar = etaDownQuar;
        this.etaMid = etaMid;
        this.etaUpQuar = etaUpQuar;
        this.etaMax = etaMax;
    }

    // Getter 方法
    public BigDecimal getEtaMin() {
        return etaMin;
    }

    public BigDecimal getEtaDownQuar() {
        return etaDownQuar;
    }

    public BigDecimal getEtaMid() {
        return etaMid;
    }

    public BigDecimal getEtaUpQuar() {
        return etaUpQuar;
    }

    public BigDecimal getEtaMax() {
        return etaMax;
    }

    @Override
    public String toString() {
        return "ReportWaferIvHour{" +
                "etaMin=" + etaMin +
                ", etaDownQuar=" + etaDownQuar +
                ", etaMid=" + etaMid +
                ", etaUpQuar=" + etaUpQuar +
                ", etaMax=" + etaMax +
                '}';
    }
}

