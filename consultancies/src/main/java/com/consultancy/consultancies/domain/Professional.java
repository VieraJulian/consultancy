package com.consultancy.consultancies.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "professionals")
@SQLDelete(sql = "UPDATE professionals SET enabled = false WHERE id = ?")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String specialty;
    @Column(nullable = false, scale = 2)
    private BigDecimal price;
    @Column(nullable = false, length = 150)
    private String description;
    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "professionalId")
    private List<Availability> availabilities;

}
