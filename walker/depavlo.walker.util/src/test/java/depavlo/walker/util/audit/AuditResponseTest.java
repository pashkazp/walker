package depavlo.walker.util.audit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

class AuditResponseTest {

	@Test
	@DisplayName("Test creation of Audit Response")
	void test1() {
		AuditResponse response = new AuditResponse();
		assertTrue(response.isEmpty());
		assertFalse(response.isValid());
		assertTrue(response.isInvalid());
		response.setValid(true);
		assertTrue(response.isValid());
		assertFalse(response.isInvalid());
		response.addMessage("key message", "message");
		assertFalse(response.isEmpty());
	}

	@Test
	@DisplayName("Test creation of Audit Response")
	void test2() {
		Multimap<String, String> messages = TreeMultimap.create();
		AuditResponse response = new AuditResponse(true, messages);
		assertTrue(response.isEmpty());
		assertTrue(response.isValid());
		messages.put("key", "message");
		assertFalse(response.isEmpty());
	}

}
