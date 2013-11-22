package hibernate.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * User: zinchenko
 * Date: 10/1/13
 */

@Entity
@Table(name = "domain")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "STATIC_DATA")
public class Domain {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "my_seq_name", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @Column(name = "domain_id")
    private String id;

    @Column(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
