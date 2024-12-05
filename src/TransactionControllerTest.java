import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Préparer l'environnement avant chaque test si nécessaire
    }

    @Test
    public void testAddTransaction() throws Exception {
        mockMvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content("{\"description\":\"Salaire\",\"type\":\"revenu\",\"amount\":1500.0}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Salaire"));
    }

    @Test
    public void testGetAllTransactions() throws Exception {
        mockMvc.perform(get("/transactions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testUpdateTransaction() throws Exception {
        // Assurez-vous que vous avez une transaction existante avec un ID spécifique
        mockMvc.perform(put("/transactions/{id}", 1)
                        .contentType("application/json")
                        .content("{\"description\":\"Salaire modifié\",\"type\":\"revenu\",\"amount\":1600.0}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Salaire modifié"));
    }

    @Test
    public void testDeleteTransaction() throws Exception {

        mockMvc.perform(delete("/transactions/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
