package theater.utility;

import org.springframework.data.jpa.repository.JpaRepository;
import theater.TestBase;
import theater.domain.entities.EntityBase;

import java.util.List;

public abstract class EntityTestBase extends TestBase {

    public static <R extends EntityBase, T extends JpaRepository<R, Long>> List<R> findAllAndPrint(T repository) {
        var results = repository.findAll();
        var size = results.size();
        if (size == 0) {
            System.out.println("No items found yet.");
        } else {
            System.out.println("Retrieved " + size + " items(-s).");
            results.forEach(EntityBase::print);
        }

        return results;
    }
}
