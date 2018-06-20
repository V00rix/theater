package theater.suites.repositories;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Repeat;
import theater.domain.entities.EntityBase;
import theater.suites.shared.GenericTestBase;

import java.util.ArrayList;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class EntityTestBase<E extends EntityBase> extends GenericTestBase<E> {
    /**
     * Save new entity to empty table
     */
    @Test
    public void create() {
        deleteAndFlush();
        var entity = createAndSave();
        repository.flush();
        var found = findExactCount(1).get(0);
        assert entity.equalz(found);
    }

    /**
     * Create distinct entities, then find each
     */
    @Test
    public void createDistinct() {
        deleteAndFlush();
        var multiple = createAndSaveMultiple();
        var found = findExactCount(multiple.size());
        assert arrayEquals(multiple, found);
    }

    /**
     * Create duplicate - assert error
     */
    @Repeat(3)
    @Test(expected = DataIntegrityViolationException.class)
    public void createDuplicatesAssertError() {
        deleteAndFlush();
        var duplicates = new ArrayList<E>();
        for (int i = 0; i < duplicateCount; i++) {
            duplicates.add(construct());
        }
        duplicateCount++;
        duplicates.forEach(EntityBase::print);
        repository.saveAll(duplicates);
        repository.flush();
    }

//    /**
//     * Update entity
//     */
//    @Test
//    public void update() {
//        deleteAndFlush();
//        var entity = createAndSave();
//        repository.flush();
//        var old = construct();
//        entity.update(constructMultiple().get(0));
//        var found = findFirstThrowIfMultipleOrNotFound();
//        assert found.equalz(entity);
//        assert !found.equalz(old);
//    }

    /**
     * Find entity by existent key
     */
    @Test
    public void findByKey() {
        deleteAndFlush();
        var multiple = createAndSaveMultiple();
        repository.flush();

        for (var entity : multiple) {
            assert entity.id != null;
            var found = repository.findById(entity.id);
            assert found.isPresent();
            assert found.get().equalz(entity);
        }

        var found = repository.findAllById(multiple.stream().map(e -> e.id).collect(Collectors.toList()));
        assert arrayEquals(multiple, found);
    }

    /**
     * Find by non-existent - assert error
     */
    @Test
    public void findNonExistentAssertError() {
        deleteAndFlush();
        var multiple = createAndSaveMultiple();
        var nonExistent = max(multiple.stream().map(e -> e.id).collect(Collectors.toList()));
        var found = repository.findById(nonExistent);
        assert !found.isPresent();
    }

    /**
     * Delete existent
     */
    @Test
    public void delete() {
        deleteAndFlush();
        var created = createAndSave();
        repository.flush();

        var id = created.id;

        repository.delete(created);
        repository.flush();

        var found = repository.findById(id);
        assert !found.isPresent();
    }

    /**
     * Delete non-existent - assert error
     */
    @Test
    public void deleteNonExistentAssertError() {
        deleteAndFlush();
        repository.delete(construct());
    }
}
