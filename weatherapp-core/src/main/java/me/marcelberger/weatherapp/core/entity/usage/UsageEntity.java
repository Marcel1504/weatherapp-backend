package me.marcelberger.weatherapp.core.entity.usage;

import jakarta.persistence.*;
import lombok.*;
import me.marcelberger.weatherapp.core.enumeration.usage.UsageModuleNameEnum;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cn_usage")
public class UsageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "module_name")
    @Enumerated(EnumType.STRING)
    private UsageModuleNameEnum moduleName;

    @Column(name = "last_activity")
    private LocalDateTime lastActivity;

    @Column(name = "total_requests")
    private Long totalRequests;
}
