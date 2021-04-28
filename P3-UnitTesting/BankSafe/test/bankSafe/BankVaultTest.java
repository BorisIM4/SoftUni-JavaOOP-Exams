package bankSafe;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankVaultTest {
    Map<String, Item> bank;
    Map<String, Item> bank2;

    @Before
    public void setUp() {
        this.bank = new LinkedHashMap<>();
        this.bank.put("A1", null);
        this.bank.put("A2", null);
        this.bank.put("A3", null);
        this.bank.put("A4", null);
        this.bank.put("B1", null);
        this.bank.put("B2", null);
        this.bank.put("B3", null);
        this.bank.put("B4", null);
        this.bank.put("C1", null);
        this.bank.put("C2", null);
        this.bank.put("C3", null);
        this.bank.put("C4", null);

        //test Add
//        Item item = new Item("Bobi", "27");
//        Item item2 = new Item("Bobi2", "222");

        this.bank2 = new LinkedHashMap<>();
        this.bank2.put("A1", null);
        this.bank2.put("A2", null);
        this.bank2.put("A3", null);
        this.bank2.put("A4", null);
        this.bank2.put("B1", null);
        this.bank2.put("B2", null);
        this.bank2.put("B3", null);
        this.bank2.put("B4", null);
        this.bank2.put("C1", null);
        this.bank2.put("C2", null);
        this.bank2.put("C3", null);
        this.bank2.put("C4", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddElementCellDontExcist() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Bobi", "27");
        bankVault.addItem("A12" ,item);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddElementCellValueExcist() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Bobi", "27");
        Item item2 = new Item("Bobi2", "222");
        bankVault.addItem("A1" ,item);
        bankVault.addItem("A1" ,item2);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testAddElementItemValueExist() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Bobi", "27");
        bankVault.addItem("A1" ,item);
        bankVault.addItem("A2" ,item);
    }

    @Test
    public void testAddElementSuccess() throws OperationNotSupportedException {
        Item item = new Item("Pesho", "1");
        String expected = "Item:1 saved successfully!";
        BankVault bankVault = new BankVault();
        String a1 = bankVault.addItem("A1", item);
        Assert.assertEquals(expected, a1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveItemCellDontExest() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Bobi", "27");
        bankVault.addItem("A1" ,item);
        bankVault.removeItem("A12", item);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveItemWhereItemDontExist() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item = new Item("Bobi", "27");
        Item item2 = new Item("Bobi2", "222");
        bankVault.addItem("A1" ,item);
        bankVault.removeItem("A1", item2);
    }

    @Test
    public void testRemoveItemPutNullInCellValue() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
//        Item item = new Item("Bobi", "27");
        Item item2 = new Item("Bobi2", "222");
//        bankVault.addItem("A1" ,item);
        bankVault.addItem("A2", item2);
        bankVault.removeItem("A2", item2);
        Map<String, Item> vaultCells = bankVault.getVaultCells();

        Assert.assertEquals(this.bank, vaultCells);
    }

    @Test
    public void testRemoveItemShouldBeSuccess() throws OperationNotSupportedException {
        BankVault bankVault = new BankVault();
        Item item2 = new Item("Bobi2", "222");
        bankVault.addItem("A2", item2);
        String actual = bankVault.removeItem("A2", item2);
        String expected = "Remove item:222 successfully!";

        Assert.assertEquals(expected, actual);
    }
}