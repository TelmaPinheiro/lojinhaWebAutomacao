package modulos.produtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


@DisplayName("Teste Web do Modulo de produtos")
public class ProdutosTest {
    @Test
    @DisplayName("NaoepermitidoRegistrarprodutoComValorIgualAZero")
    public void testNaoepermitidoRegistrarprodutoComValorIgualAZero() {
        // Abrir o navegdor
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        // Vou maximizar a tela
        navegador.manage().window().maximize();
        //Vou definir um tempo de espera padrão de até 5 segundos, caso falhar ele navegador esperar
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //o navegador é como se fosse o browser e a partir do manage tá pedindo pra ele maximizar

        // navegador para pagina da Lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web-bugada/v2/");

        // Fazer Login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");

        // tem 2 para usuário pq tem 2 estado dele, o 1 cursor normal e 2 estado pra cima
        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");
        navegador.findElement(By.cssSelector("button[type='submit']")).click();


        // Vou para tela de registro de Produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        // Vou preencher dados do produto e o valor sra igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys("Macbook Pro");
        navegador.findElement(By.name("produtovalor")).sendKeys("000");

        // nesse de cima poderia colocar tb o id
        navegador.findElement(By.id("produtocores")).sendKeys("preto, branco");

        // Vou submeter o formulário
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou validar que a mensagem de erro foi apresentada
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);

        // Vou fechar o navegador
        navegador.quit();

    }


}
