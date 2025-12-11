import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import java.io.StringReader;

class BankAccountTest {
    private BankAccount account;
    
    @BeforeEach
    void setUp() {
        account = new BankAccount("1234567890",1000);
    }
     @Test
    @DisplayName("Тест получения баланса")
    void testGetBalance() {
        assertEquals(1000, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Тест получения номера счета")
    void testGetAccountNumber() {
        assertEquals("1234567890", account.getAccountNumber());
    }
    @Test
    @DisplayName("Тест проверки активности счета")
    void testIsActive() {
        assertTrue(account.isActive());
    }
    @Test
    @DisplayName("Тест расчета процентов для активного счета")
    void testCalculateMonthlyInterest_ActiveAccount() {
        double interest = account.calculateMonthlyInterest(5.0);
        assertEquals(4.1667, interest, 0.0001);
    }
    
    @Test
    @DisplayName("Тест расчета процентов для неактивного счета")
    void testCalculateMonthlyInterest_InactiveAccount() {
        // Деактивируем счет и проверяем, что проценты равны 0
        account.deactivate();
        double interest = account.calculateMonthlyInterest(5.0);
        assertEquals(0.0, interest, 0.001);
    }
    
    @Test
    @DisplayName("Тест расчета процентов с разными ставками")
    void testCalculateMonthlyInterest_DifferentRates() {

        BankAccount testAccount = new BankAccount("1111111111", 1000);
        

        double interest1 = testAccount.calculateMonthlyInterest(5.0);
        assertEquals(4.1667, interest1, 0.0001);
        
        double interest2 = testAccount.calculateMonthlyInterest(12.0);
        assertEquals(10.0, interest2, 0.001);
        
        double interest3 = testAccount.calculateMonthlyInterest(0.0);
        assertEquals(0.0, interest3, 0.001);
    }
    
    // 2. Тестирование генерации исключений
    
    @Test
    @DisplayName("Тест конструктора с null номером счета")
    void testConstructor_NullAccountNumber() {

        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(null, 1000);
        });
    }
    
    @Test
    @DisplayName("Тест конструктора с отрицательным балансом")
    void testConstructor_NegativeInitialBalance() {

        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("1234567890", -100);
        });
    }
    
    @Test
    @DisplayName("Тест пополнения неактивного счета")
    void testDeposit_InactiveAccount() {

        account.deactivate();
        assertThrows(IllegalStateException.class, () -> {
            account.deposit(100);
        });
    }
    
    @Test
    @DisplayName("Тест пополнения отрицательной суммой")
    void testDeposit_NegativeAmount() {

        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-50);
        });
    }
    
    @Test
    @DisplayName("Тест снятия при недостатке средств")
    void testWithdraw_InsufficientFunds() {

        assertThrows(InsufficientFundsException.class, () -> {
            account.withdraw(1500);
        });
    }
    
    @Test
    @DisplayName("Тест снятия с невалидными параметрами")
    void testWithdraw_InvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-10);
        });
        
        account.deactivate();
        assertThrows(IllegalStateException.class, () -> {
            account.withdraw(100);
        });
    }
    
    @Test
    @DisplayName("Тест перевода на null счет")
    void testTransfer_NullTarget() {

        assertThrows(IllegalArgumentException.class, () -> {
            account.transfer(null, 100);
        });
    }
    
    @Test
    @DisplayName("Тест перевода на неактивный счет")
    void testTransfer_InactiveTarget() {

        BankAccount targetAccount = new BankAccount("2222222222", 200);
        targetAccount.deactivate();
        

        assertThrows(IllegalStateException.class, () -> {
            account.transfer(targetAccount, 100);
        });
    }
    
    // 3. 
    
    @Test
    @DisplayName("Тест успешного пополнения счета")
    void testDeposit_ValidParameters_NoException() {
       
        assertDoesNotThrow(() -> {
            account.deposit(500);
        });
        
        assertEquals(1500, account.getBalance(), 0.001);
    }
    
    @Test
    @DisplayName("Тест успешного снятия средств")
    void testWithdraw_ValidParameters_NoException() {

        assertDoesNotThrow(() -> {
            account.withdraw(500);
        });

        assertEquals(500, account.getBalance(), 0.001);
    }
    
    @Test
    @DisplayName("Тест успешного перевода")
    void testTransfer_ValidParameters_NoException() {

        BankAccount targetAccount = new BankAccount("9999999999", 500);
        

        assertDoesNotThrow(() -> {
            account.transfer(targetAccount, 300);
        });
        
        assertEquals(700, account.getBalance(), 0.001);
        assertEquals(800, targetAccount.getBalance(), 0.001);
    }
    
    @Test
    @DisplayName("Тест успешного расчета процентов")
    void testCalculateMonthlyInterest_PositiveRate_NoException() {

        assertDoesNotThrow(() -> {
            account.calculateMonthlyInterest(5.0);
        });
    }
    
    // 4. 
    
    @Test
    @DisplayName("Тест чтения валидных данных")
    void testReadFromReader_ValidData() throws IOException {

        String validData = "1234567890,1500.0,true";
        StringReader reader = new StringReader(validData);
        

        BankAccount readAccount = BankAccount.readFromReader(reader);
        

        assertNotNull(readAccount);
        assertEquals("1234567890", readAccount.getAccountNumber());
        assertEquals(1500, readAccount.getBalance(), 0.001);
        assertTrue(readAccount.isActive());
    }
    
    @Test
    @DisplayName("Тест чтения неактивного счета")
    void testReadFromReader_InactiveAccount() throws IOException {

        String inactiveData = "9876543210,750.0,false";
        StringReader reader = new StringReader(inactiveData);

        BankAccount readAccount = BankAccount.readFromReader(reader);

        assertNotNull(readAccount);
        assertFalse(readAccount.isActive());
    }
    
    @Test
    @DisplayName("Тест чтения с неверным форматом данных")
    void testReadFromReader_InvalidFormat() {

        String invalidData = "invalid,data,format";
        StringReader reader = new StringReader(invalidData);
        

        assertThrows(IOException.class, () -> {
            BankAccount.readFromReader(reader);
        });
    }
    
    @Test
    @DisplayName("Тест чтения с пустыми данными")
    void testReadFromReader_EmptyData() {

        String emptyData = "";
        StringReader reader = new StringReader(emptyData);
        
        
        assertThrows(IOException.class, () -> {
            BankAccount.readFromReader(reader);
        });
    }
    
}