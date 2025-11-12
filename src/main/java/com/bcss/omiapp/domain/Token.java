package com.bcss.omiapp.domain;

import com.bcss.omiapp.auth.Rol;
import com.bcss.omiapp.auth.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idToken;
    @Column(unique = true)
    private String token;
    @Column(nullable = false)
    private Boolean isRevoked;
    @Column(nullable = false)
    private Boolean isExpired;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TokenType type = TokenType.BEARER;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador")
    private Trabajador trabajador;

}
