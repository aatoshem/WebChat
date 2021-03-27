package com.leidos.webchat.model;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class AuditInfo {

    @Column
    private String createdBy;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String updatedBy;

    @Column
    private LocalDateTime updatedAt;

}
