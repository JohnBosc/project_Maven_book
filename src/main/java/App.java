import library.Author;
import library.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App{
	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String argv[]) throws Exception{
		System.out.println("Running App ...");

		log.debug("Create persistence manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();

		log.debug("Create entities");
		Author jean = new Author();
		jean.setFirstName("Jean");
		jean.setLastName("Mengue");

		Author leonie = new Author();
		leonie.setFirstName("Leonie");
		leonie.setLastName("Moukouri");

		Author eric = new Author();
		eric.setFirstName("Eric");
		eric.setLastName("Mengue");

		Book book = new Book();
		book.setIsbn("978-2-212-11408-9");
		book.setTitle("OOP - Java");
		book.setPrice(45.0);

		book.getAuthors().add(jean);
		book.getAuthors().add(leonie);
		book.getAuthors().add(eric);

		log.debug("Persist entities in a transaction");
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.persist(jean);
		em.persist(leonie);
		em.persist(eric);

		em.persist(book);

		transaction.commit();

		log.debug("Close Entities Manager");
		em.close();
		emf.close();
	}
} 
