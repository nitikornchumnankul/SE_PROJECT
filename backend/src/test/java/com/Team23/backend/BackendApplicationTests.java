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
//-------------------------------------------------------testMedicalSuppliesPass------------------------------------------------------
	@Test
	public void testMedicalSuppliesPass() {
		MedicalSupplies   medicalSupplies    = new MedicalSupplies();
		medicalSupplies.setCodeNumber("M59741");
		medicalSupplies.setMedicalsuppliesName("ABCDE");
		medicalSupplies.setBrandName("STD");
		medicalSupplies.setModelNumber("ADED");
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

//-------------------------------------------------------testMedicalSuppliesNotNull------------------------------------------------------
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

//-------------------------------------------------------testMedicalSuppliesless2----------------------------------------------------	
    @Test
	public void testMedicalSuppliesless2() {
		MedicalSupplies   medicalSupplies    = new MedicalSupplies();
		medicalSupplies.setMedicalsuppliesName("a");
		try {
            entityManager.persist(medicalSupplies);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n\n\n\n");
			System.out.println("-------------------------------------------------------testMedicalSuppliesless2------------------------------------------------------");
			System.out.println( e.getConstraintViolations());
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n");
		}
	}

//-------------------------------------------------------testMedicalSuppliesthan30------------------------------------------------------
	   @Test
		public void testMedicalSuppliesthan30() {
		MedicalSupplies   medicalSupplies    = new MedicalSupplies();
		medicalSupplies.setMedicalsuppliesName("sajkdfhwiuehfjkshdhfiuwehiufhiksdhaiuhfuwheiuhufhoiuaswhdfiuhweuhdifhwidufhiw");
		try {
			entityManager.persist(medicalSupplies);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n\n\n\n");
			System.out.println("-------------------------------------------------------testMedicalSuppliesthan30------------------------------------------------------");
			System.out.println( e.getConstraintViolations());
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n");
		}

	}

//-------------------------------------------------------MedicalSuppliesNoHaveSpecialCharacter------------------------------------------------------
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
	//-------------------------------------------------------testMedicalSuppliesunique------------------------------------------------------
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
//-------------------------------------------------------testcodeNumberunique------------------------------------------------------
	@Test
	public void testcodeNumberunique(){
		MedicalSupplies medicalsupplies  = new MedicalSupplies();
		MedicalSupplies medicalSuppliesunique = new MedicalSupplies();
		medicalsupplies.setMedicalsuppliesName("ABCDE"); //Message MedicalSupplies should not null มีข้อความนี้มาจึงต้องเพิ่ม setMedicalSuppliesName
		medicalSuppliesunique.setMedicalsuppliesName("ABCDED");

		medicalsupplies.setCodeNumber("M");
		medicalSuppliesunique.setCodeNumber("M");
	try{

		entityManager.persist(medicalsupplies);
		entityManager.flush();
		entityManager.persist(medicalSuppliesunique);
		entityManager.flush();
		fail("Should not pass to this line");
	}catch(javax.persistence.PersistenceException e){
		System.out.println("\n\n\n\n\n");
		System.out.println("-------------------------------------------------------testcodeNumberunique------------------------------------------------------");
		System.out.println(e+"testcodeNumberunique");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n\n\n");
	}

	}
//-------------------------------------------------------modelNumberuque------------------------------------------------------
	@Test
	public void testmodelNumberuque(){
		MedicalSupplies medicalSupplies  = new MedicalSupplies();
		MedicalSupplies medicalSupplies1 = new MedicalSupplies();
		medicalSupplies.setMedicalsuppliesName("Mask");
		medicalSupplies1.setMedicalsuppliesName("Masks");
		medicalSupplies.setModelNumber("MODEL");
		medicalSupplies1.setModelNumber("MODEL");
	try{
		entityManager.persist(medicalSupplies);
		entityManager.flush();
		entityManager.persist(medicalSupplies1);
		entityManager.flush();

	}catch(javax.persistence.PersistenceException e){
		System.out.println("\n\n\n\n");
		System.out.println("-------------------------------------------------------testmodelNumberuque------------------------------------------------------");
		System.out.println(e+"testmodelNumberuque");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n");
	}

	}
//--------------------------------------------------------------------testbrandNameshort-------------------------------------------------------------
	@Test
	public void testbrandNameshort(){
		MedicalSupplies medicalsupplies = new MedicalSupplies();
		medicalsupplies.setMedicalsuppliesName("ABCD");
		medicalsupplies.setBrandName("a");
		try{
			entityManager.persist(medicalsupplies);
			entityManager.flush();

		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations =e.getConstraintViolations();
			assertEquals(violations.isEmpty(),false);
			assertEquals(violations.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------testbrandName-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");			
		}

	}
//--------------------------------------------------------------------testbrandNamelong-------------------------------------------------------------
	@Test
	public void testbrandNamelong(){
		MedicalSupplies medicalsupplies = new MedicalSupplies();
		medicalsupplies.setMedicalsuppliesName("Mask");
		medicalsupplies.setBrandName("askdhifoihidohfiwhioekdhfpowsiehfkdkofwiheofihosdfieidkfhowiehfodkfhoekidowfoijdfieik");
		try{
			entityManager.persist(medicalsupplies);
			entityManager.flush();
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);

			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------testbrandNamelong-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");	
			
		}
	}
//--------------------------------------------------------------------testpropertiesshort-------------------------------------------------------------
	@Test
	public void testpropertiesshort(){
		MedicalSupplies medicalSupplies =new MedicalSupplies();
		medicalSupplies.setMedicalsuppliesName("Mask");
		medicalSupplies.setProperties("d");
		try{
			entityManager.persist(medicalSupplies);
			entityManager.flush();
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> validation =e.getConstraintViolations();
			assertEquals(validation.isEmpty(),false);
			assertEquals(validation.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------testpropertiesshort-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");	
		}
	}
//--------------------------------------------------------------------testpropertieslong-------------------------------------------------------------

	@Test
	public void testpropertieslong(){
		MedicalSupplies medicalSupplies = new MedicalSupplies();
		medicalSupplies.setMedicalsuppliesName("Mask");
		medicalSupplies.setProperties("ihsoadfihsoidhfoihwkdofjowihdofihwihodhsifhowoiheifhoiwheifoihididifowhiefhooihef");
		
		try{
			entityManager.persist(medicalSupplies);
			entityManager.flush();

		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> validation = e.getConstraintViolations();
			assertEquals(validation.isEmpty(),false);
			assertEquals(validation.size(),1);
			System.out.println("\n\n\n\n\n\n\n\n");
			System.out.println("-----------------------------------------testpropertieslong-----------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n\n");

		}

	}
}

