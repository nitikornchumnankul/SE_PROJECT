package com.Team23.backend;
import static org.junit.Assert.assertEquals;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import com.Team23.backend.Entity.*;
import com.Team23.backend.Repository.MedicalSuppliesRepository;
import static org.junit.Assert.fail;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BackendApplicationTests {
	@Autowired
	private MedicalSuppliesRepository medicalSuppliesRepository;

	@Autowired
    private TestEntityManager entityManager;

	private Validator validator;
	
	@Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@Test
	public void testMedicalSuppliesPass() {
		MedicalSupplies   medicalSupplies    = new MedicalSupplies();
		medicalSupplies.setMedicalsuppliesName("ABCDE");

		try {
            entityManager.persist(medicalSupplies);
            entityManager.flush();
			System.out.println("\n\n\n\n\n");
			System.out.println("-------------------------------------------------------testMedicalSuppliesPass------------------------------------------------------");
			System.out.println("testMedicalSuppliesPass");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n");
            
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			
			
		}

		
	}
	@Test
	public void testMedicalSuppliesNotNull() {
		MedicalSupplies   medicalSupplies    = new MedicalSupplies();
		medicalSupplies.setMedicalsuppliesName(null);

		try {
            entityManager.persist(medicalSupplies);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n\n\n\n");
			System.out.println("-------------------------------------------------------testMedicalSuppliesNotNull------------------------------------------------------");
			System.out.println( e.getConstraintViolations());
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n");
		}

		
	}
    @Test
	public void testMedicalSuppliesless3() {
		MedicalSupplies   medicalSupplies    = new MedicalSupplies();
		medicalSupplies.setMedicalsuppliesName("ab");
		try {
            entityManager.persist(medicalSupplies);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n\n\n\n");
			System.out.println("-------------------------------------------------------testMedicalSuppliesless3------------------------------------------------------");
			System.out.println( e.getConstraintViolations());
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n");
		}
	}
	   @Test
		public void testMedicalSuppliesthan10() {
		MedicalSupplies   medicalSupplies    = new MedicalSupplies();
		medicalSupplies.setMedicalsuppliesName("abcdefghtjkl");
		try {
			entityManager.persist(medicalSupplies);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n\n\n\n");
			System.out.println("-------------------------------------------------------testMedicalSuppliesthan10------------------------------------------------------");
			System.out.println( e.getConstraintViolations());
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n");
		}

	}

	@Test
	public void testMedicalSuppliesNoHaveSpecialCharacter() {
	MedicalSupplies   medicalSupplies    = new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName("%$#days");
	try {
		entityManager.persist(medicalSupplies);
		entityManager.flush();

		fail("Should not pass to this line");
	} catch(javax.validation.ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		assertEquals(violations.isEmpty(), false);
		assertEquals(violations.size(), 1);
		System.out.println("\n\n\n\n\n");
		System.out.println("-------------------------------------------------------MedicalSuppliesNoHaveSpecialCharacter------------------------------------------------------");
		System.out.println( e.getConstraintViolations());
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n");	
	}

	}
	@Test
	public void testMedicalSuppliesunique() {
	MedicalSupplies   medicalSupplies    = new MedicalSupplies();
	MedicalSupplies   medicalSuppliesunique    = new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName("days");
	medicalSuppliesunique.setMedicalsuppliesName("days");
	try {
		entityManager.persist(medicalSupplies);
		entityManager.flush();
		entityManager.persist(medicalSuppliesunique);
		entityManager.flush();


		fail("Should not pass to this line");
	} catch(javax.persistence.PersistenceException e) {
		// Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		// assertEquals(violations.isEmpty(), false);
		// assertEquals(violations.size(), 1);
		System.out.println("\n\n\n\n\n");
		System.out.println("-------------------------------------------------------testMedicalSuppliesunique------------------------------------------------------");
		System.out.println( e+"testMedicalSuppliesunique");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n");	
	}

	}




}

