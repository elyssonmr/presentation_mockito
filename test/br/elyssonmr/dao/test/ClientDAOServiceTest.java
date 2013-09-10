package br.elyssonmr.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.elyssonmr.dao.exception.DAOException;
import br.elyssonmr.facade.DAOFacade;
import br.elyssonmr.model.Client;

public class ClientDAOServiceTest {

	private DAOFacade<Client> dao;

	@Before
	public void setUp() throws Exception {
		// Objects setups
		Client cliente = new Client("Élysson MR", "Inatel",
				"elysson@inatel.br", 23);
		Client readClient = new Client(1, "Élysson MR", "Inatel",
				"elysson@inatel.br", 23);
		Client updateCliente = new Client(1, "Élysson", "SRS",
				"elysson@inatel.br", 23);

		Client errorUpdate = new Client(100, "Élysson", "SRS",
				"elysson@inatel.br", 23);

		List<Client> nameCriteria = new ArrayList<Client>();
		nameCriteria.add(new Client(1, "Ana", "Inatel", "ana@inatel.br", 19));
		nameCriteria.add(new Client(2, "Ana Paula", "Inatel",
				"ana_paula@inatel.br", 35));

		Map<String, Object> criteriaName = new HashMap<>();
		criteriaName.put("NAME_BEGIN", "Ana");

		List<Client> CriteriaAge = new ArrayList<Client>();
		CriteriaAge.add(new Client(1, "Ana", "Inatel", "ana@inatel.br", 20));
		CriteriaAge.add(new Client(2, "Ana Paula", "Inatel",
				"ana_paula@inatel.br", 20));
		CriteriaAge.add(new Client(3, "Ana Paula", "Inatel",
				"ana_paula@inatel.br", 20));
		CriteriaAge.add(new Client(4, "Ana Paula", "Inatel",
				"ana_paula@inatel.br", 20));
		CriteriaAge.add(new Client(5, "Ana Paula", "Inatel",
				"ana_paula@inatel.br", 20));

		Map<String, Object> criteriaAge = new HashMap<>();
		criteriaAge.put("AGE", 20);

		// Moking
		this.dao = Mockito.mock(DAOFacade.class);
		Mockito.when(dao.create(cliente)).thenReturn((long) 1);
		Mockito.when(dao.update(updateCliente)).thenReturn(updateCliente);
		Mockito.when(dao.readById(1)).thenReturn(readClient);
		Mockito.when(dao.readByCriteria(criteriaName)).thenReturn(nameCriteria);
		Mockito.when(dao.readByCriteria(criteriaAge)).thenReturn(CriteriaAge);
		Mockito.when(dao.update(errorUpdate)).thenThrow(DAOException.class);
	}

	@Test
	public void createTest() {
		try {
			Client cliente = new Client("Élysson MR", "Inatel",
					"elysson@inatel.br", 23);
			long id = dao.create(cliente);
			assertEquals(1, id);
		} catch (DAOException e) {
			fail("Should not throw a Exception");
		}
	}

	@Test
	public void readByIdTest() {
		try {
			Client expected = new Client("Élysson MR", "Inatel",
					"elysson@inatel.br", 23);
			expected.setId(1);
			Client cliente = dao.readById(1);
			assertEquals(expected, cliente);
		} catch (DAOException e) {
			fail("Should not throw a Exception");
		}
	}

	@Test
	public void readByCriteriaByNameTest() {
		try {
			List<Client> expected = new ArrayList<Client>();
			expected.add(new Client(1, "Ana", "Inatel", "ana@inatel.br", 19));
			expected.add(new Client(2, "Ana Paula", "Inatel",
					"ana_paula@inatel.br", 35));
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("NAME_BEGIN", "Ana");
			List<Client> clientes = dao.readByCriteria(criteria);
			assertEquals(expected, clientes);
		} catch (DAOException e) {
			fail("Should not throw a Exception");
		}
	}

	@Test
	public void readByCriteriaByAgeTest() {
		try {
			List<Client> expected = new ArrayList<Client>();
			expected.add(new Client(1, "Ana", "Inatel", "ana@inatel.br", 20));
			expected.add(new Client(2, "Ana Paula", "Inatel",
					"ana_paula@inatel.br", 20));
			expected.add(new Client(3, "Ana Paula", "Inatel",
					"ana_paula@inatel.br", 20));
			expected.add(new Client(4, "Ana Paula", "Inatel",
					"ana_paula@inatel.br", 20));
			expected.add(new Client(5, "Ana Paula", "Inatel",
					"ana_paula@inatel.br", 20));
			Map<String, Object> criteria = new HashMap<>();
			criteria.put("AGE", 20);
			List<Client> clientes = dao.readByCriteria(criteria);
			assertEquals(expected, clientes);
		} catch (DAOException e) {
			fail("Should not throw a Exception");
		}
	}

	@Test
	public void updateTest() {
		try {
			Client updateCliente = new Client(1, "Élysson", "SRS",
					"elysson@inatel.br", 23);
			Client updatedCliente = dao.update(updateCliente);
			assertEquals(updateCliente, updatedCliente);
		} catch (DAOException e) {
			fail("Should not throw a Exception");
		}
	}

	@Test(expected = DAOException.class)
	public void updateErrorTest() throws DAOException {
		Client errorUpdate = new Client(100, "Élysson", "SRS",
				"elysson@inatel.br", 23);
		dao.update(errorUpdate);
	}
}
