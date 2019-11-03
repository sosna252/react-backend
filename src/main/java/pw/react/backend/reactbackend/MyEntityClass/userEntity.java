package pw.react.backend.reactbackend.MyEntityClass;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class userEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "dateofbirth")
    private String dateofbirth;

    @Column(name = "active")
    private String active;

    public userEntity()
    {this.id = null;}
    public userEntity(Integer id)
    {this.id=id;}
    public userEntity(String login, String firstname, String lastname, String dateofbirth, String active)
    {
        this.id=id;
        this.login = login;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth=dateofbirth;
        this.active=active;
    }

    public void setId(int id){this.id=id;}
    public void setLogin(String login){this.login=login;}
    public void setFirstname(String firstname){this.firstname=firstname;}
    public void setLastname(String lastname){this.lastname=lastname;}
    public void setDateofbirth(String dateofbirth){this.dateofbirth=dateofbirth;}
    public void setActive(String active){this.active=active;}

    public Integer getId(){return this.id;}
    public String getLogin(){return this.login;}
    public String getFirstname(){return this.firstname;}
    public String getLastname(){return this.lastname;}
    public String getDateofbirth(){return this.dateofbirth;}
    public String getActive(){return this.active;}


}
