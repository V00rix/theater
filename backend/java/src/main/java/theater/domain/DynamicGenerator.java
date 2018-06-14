package theater.domain;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

public class DynamicGenerator extends IdentityGenerator {

    public Serializable generate(SessionImplementor session, Object object)
            throws HibernateException {

        return new IdentityGenerator().generate(session, object);
    }
}