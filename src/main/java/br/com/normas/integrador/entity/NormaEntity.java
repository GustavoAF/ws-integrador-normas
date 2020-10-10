package br.com.normas.integrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "norma", schema = "bdnorma")
public class NormaEntity implements Serializable {

    @Id
    @Column(name = "codigo")
    private String id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_public")
    private Date data_public;

    @Column(name = "status")
    private String status;

}
