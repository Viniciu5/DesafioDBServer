package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Teste {
	
	static WebDriver driver;

	@BeforeClass
	public void inicia() {
		System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		Compra(driver);
	}
	
	@Test	
	public void Compra(WebDriver driver) {
		
		driver.get("http://www.automationpractice.com");
		
		//Escolhe item
		WebElement item = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[5]/div/div[2]/div[2]/a[1]"));
		item.click();
		
		//Vai para o Checkout
		WebDriverWait wait1 = new WebDriverWait(driver, 2);
		wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")));
		WebElement confirma1 = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span"));
		confirma1.click();
		
		//Valida o item
		assertEquals("Printed Summer Dress", driver.findElement(By.xpath("//*[@id=\"product_5_19_0_0\"]/td[2]/p")).getText());
		assertEquals("1", driver.findElement(By.xpath("//*[@id=\"product_5_19_0_0\"]/td[5]/input[2]")).getAttribute("value"));
		
		//Confirma a compra
		WebElement confirma2 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span"));
		confirma2.click();
		
		//Insere e-mail
		WebElement email = driver.findElement(By.id("email_create"));
		email.sendKeys("vlrf.01@gmail.com");
		WebElement confirma3 = driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span"));
		confirma3.click();
		
		//Preenche Formulario
		WebDriverWait wait2 = new WebDriverWait(driver, 2);
		wait2.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("page")));
		//Confirma se usuario ja existe...
		if(driver.findElements( By.id("id_gender1") ).size() != 0) {
			//Se nao existe, cria um novo...
			WebElement sexo = driver.findElement(By.id("id_gender1"));
			sexo.click();
			WebElement nome1 = driver.findElement(By.id("customer_firstname"));
			nome1.sendKeys("Vinicius");
			WebElement snome1 = driver.findElement(By.id("customer_lastname"));
			snome1.sendKeys("Favero");
			WebElement senha = driver.findElement(By.id("passwd"));
			senha.sendKeys("psw123456");
			WebDriverWait wait3 = new WebDriverWait(driver, 2);
			wait3.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("uniform-days")));
			WebElement dia = driver.findElement(By.id("uniform-days"));
			dia.click();
			WebElement sdia = driver.findElement(By.xpath("//*[@id=\"days\"]/option[23]"));
			sdia.click();		
			WebElement mes = driver.findElement(By.id("uniform-months"));
			mes.click();
			WebElement smes = driver.findElement(By.xpath("//*[@id=\"months\"]/option[13]"));
			smes.click();
			WebElement ano = driver.findElement(By.id("uniform-years"));
			ano.click();
			WebElement sano = driver.findElement(By.xpath("//*[@id=\"years\"]/option[26]"));
			sano.click();
			WebElement endr1 = driver.findElement(By.id("address1"));
			endr1.sendKeys("2066 Crist Drive");
			WebElement cidade = driver.findElement(By.id("city"));
			cidade.sendKeys("Los Altos");
			WebElement estado = driver.findElement(By.id("id_state"));
			estado.click();
			WebElement sestado = driver.findElement(By.xpath("//*[@id=\"id_state\"]/option[6]"));
			sestado.click();
			WebElement cep = driver.findElement(By.id("postcode"));
			cep.sendKeys("94024");
			WebElement cel = driver.findElement(By.id("phone_mobile"));
			cel.sendKeys("51984776915");
			WebElement endr2 = driver.findElement(By.id("alias"));
			endr2.clear();
			endr2.sendKeys("2101 Waverley Street, Palo Alto");
			WebElement confirma4 = driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span"));
			confirma4.click();
		}else {
			//Se existe, apenas insere as credenciais...
			WebElement emailc = driver.findElement(By.id("email"));
			emailc.sendKeys("vlrf.01@gmail.com");
			WebElement senhac = driver.findElement(By.id("passwd"));
			senhac.sendKeys("psw123456");
			WebElement confirmac = driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span"));
			confirmac.click();
		}
		
		//Valida Endereco
		String validaend = driver.findElement(By.id("address_delivery")).getText();
		assertTrue(validaend.contains("Vinicius Favero" + "\n2066 Crist Drive" + "\nLos Altos, California 94024" + "\nUnited States" + "\n51984776915"));
		WebElement confirma5 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span"));
		confirma5.click();
		
		//Aceita termos
		WebElement aceitat = driver.findElement(By.id("cgv"));
		aceitat.click();
		WebElement confirma6 = driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span"));
		confirma6.click();
		
		//Valida o valor
		assertEquals("$30.98", driver.findElement(By.id("total_price_container")).getText());
		
		//Confima Pagamento
		WebElement confirmap1 = driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a"));
		confirmap1.click();
		
		//Confirma a Compra
		WebElement confirmap2 = driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span"));
		confirmap2.click();
		
		//Validacao Final
		assertEquals("Your order on My Store is complete.", driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p/strong")).getText());	
		
	}
	
}
