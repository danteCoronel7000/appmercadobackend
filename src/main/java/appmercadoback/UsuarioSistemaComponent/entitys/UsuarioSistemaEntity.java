package appmercadoback.UsuarioSistemaComponent.entitys;


import appmercadoback.personaComponent.entitys.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_sistema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioSistemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String estado;
    private String role;
    @OneToOne
    @JoinColumn(name = "persona_id")
    private PersonaEntity persona;
}