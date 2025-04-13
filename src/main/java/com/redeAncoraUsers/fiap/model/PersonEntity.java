package com.redeAncoraUsers.fiap.model;

import com.redeAncoraUsers.fiap.validators.PersonPostData;
import com.redeAncoraUsers.fiap.validators.PersonUpdateData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Person")
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "SEQ_PERSON", allocationSize = 1)
    private Long id;

    private String name;
    private String cpf_cnpj;
    private String phone;
    private String address;

    public PersonEntity(@Valid PersonPostData data) {
        this.name = data.name();
        this.cpf_cnpj = data.cpf_cnpj();
        this.phone = data.phone();
        this.address = data.address();
    }


    public void updateInfo(@Valid PersonUpdateData data) {
        if (data == null) {
            return;
        }
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.cpf_cnpj() != null) {
            this.cpf_cnpj = data.cpf_cnpj();
        }
        if (data.phone() != null) {
            this.phone = data.phone();
        }
        if (data.address() != null) {
            this.address = data.address();
        }
    }

}

