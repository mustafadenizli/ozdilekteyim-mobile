import com.thoughtworks.gauge.Logger;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class StepImplementation extends BaseTest {

    @Step("<time> saniye bekle")
    public void waitTime(int time) throws InterruptedException {
        Thread.sleep(1000 * time);
    }
    @Step("<key> texti ile uygulamanın acildigi kontrol edilir")
    public void compareValue(String key) {
        String realValue = appiumDriver.findElement(By.xpath("//*[@text='Markalar']")).getText().trim();
        Assert.assertEquals("hatalı değer", realValue, key);
        LOGGER.info("uygulamanın acildigi kontrol edildi.");
    }
    @Step("alisverise basla butonuna tıklanır")
    public void alisverisButtonClick() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
          //      By.id("com.ozdilek.ozdilekteyim:id/tv_startShoppingStore"))).click();
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/tv_startShoppingStore")).click();
    }
    @Step("<key> texti ile Alisveris sayfasının acildigi dogrulanir")
    public void alisverisSayfasiChecked(String key) {
        String realValue = appiumDriver.findElement(By.xpath("//*[@text='Erkek']")).getText();
        Assert.assertEquals("hatalı değer", realValue, key);
        LOGGER.info("Alışveriş sayfasının açıldığı kontrol edildi.");
    }
    @Step("<key> tiklanır ve Kategoriler sayfası acilir")
    public void KategoriClick(String key) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
          //      By.id(key))).click();
        appiumDriver.findElement(By.id(key)).click();
        LOGGER.info("Kategoriler sayfasını acildi");
    }

    @Step("<key> Kategoriler sayfasinin acildigi dogrulanir")
    public void kategoriPageChecked(String key) {
        String realValue = appiumDriver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc='Kategoriler']/android.widget.TextView")).getText();
        Assert.assertEquals("hatalı eşleşme", realValue, key);
        LOGGER.info("Kategoriler sayfasinin acildigi dogrulandi");

    }

    @Step("Menüden Kadın secenegine tiklanir")
    public void SelectWoman() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
          //      By.xpath("//*[@text='Kadın']"))).click();
        appiumDriver.findElement(By.xpath("//*[@text='Kadın']")).click();
        LOGGER.info("Menuden kadin secildi");

    }

    @Step("Pantolon Kategorisine tiklanir")
    public void clickPantolonCategory() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
          //      By.xpath("//*[@text='Pantolon']"))).click();
        appiumDriver.findElement(By.xpath("//*[@text='Pantolon']")).click();
        LOGGER.info("Pantolon kategorisine girildi");
    }

    @Step("sayfa 2 defa asagiya dogru scrool edilir")
    public void pageScrool() throws InterruptedException {

        Dimension dimension = appiumDriver.manage().window().getSize();
        int start_x = (int) (dimension.width * 0.5);
        int start_y = (int) (dimension.height * 0.8);

        int end_x = (int) (dimension.width * 0.2);
        int end_y = (int) (dimension.height * 0.2);
        for (int i = 0; i < 2; i++) {
            TouchAction touch = new TouchAction(appiumDriver);
            touch.press(PointOption.point(start_x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(end_x, end_y)).release().perform();
        }
        LOGGER.info("sayfa scroll edildi");
    }
    @Step("rastgele bir urun secilir")
    public void selectRandomProduct() {
        Random rastgele = new Random();

        List<MobileElement> selectProduct = appiumDriver.findElements(By.xpath(
                "//*[@resource-id='com.ozdilek.ozdilekteyim:id/recyclerView']//android.view.ViewGroup"));
        int kacinciEleman = rastgele.nextInt(selectProduct.size());

        selectProduct.get(kacinciEleman).click();
        LOGGER.info("rastgele urun secimi yapildi.");
    }

    @Step("<key> texti ile urun detay sayfasının kontrolu yapilir")
    public void productDetailControl(String key) {
        String realValue = appiumDriver.findElement(By.xpath("//*[@text='Renk:']")).getText();
        Assert.assertEquals("hatalı değer", realValue, key);
        LOGGER.info("urun detay sayfasının kontrolu yapildi");
    }

    @Step("favoriler butonuna tiklanir")
    public void favoriButtonClick() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
          //      By.id("com.ozdilek.ozdilekteyim:id/imgFav"))).click();
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/imgFav")).click();
        LOGGER.info("favoriler butonuna tiklandi");
    }

    @Step("<key> texti ile giris yapma sayfasinin acildigi dogrulanir")
    public void loginPageControl(String key) {
        String realValue = appiumDriver.findElement(By.xpath("//*[@text='Beni Hatırla']")).getText();
        Assert.assertEquals("hatalı değer", realValue, key);
        LOGGER.info("giris yapma sayfasinin acildigi dogrulandi");
    }

    @Step("<key> kullanici adi texti bul ve <key1> kullanici adi gir")
    public void userName(String key, String key1) {
        MobileElement kullaniciKey = appiumDriver.findElement(By.id(key));
        kullaniciKey.sendKeys(key1);
        LOGGER.info("kullanici adi alani bulundu ve tiklandi");
    }

    @Step("<key> sifre texti bul <key1> sifre gir")
    public void Password(String key, String key1) {
        MobileElement sifreKey = appiumDriver.findElement(By.id(key));
        sifreKey.sendKeys(key1);
        LOGGER.info("password alani bulundu ve tiklandi");
    }

    @Step("<key> elementi bul tikla")
    public void loginButton(String key) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
        //        By.id("com.ozdilek.ozdilekteyim:id/btnLogin"))).click();
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/btnLogin")).click();
        LOGGER.info("giris yap butonuna tiklandi");
    }

    @Step("geri butonuna iki kere tiklanir")
    public void doubleClickBackButton() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
          //      By.id("com.ozdilek.ozdilekteyim:id/ivBack"))).click();
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/ivBack")).click();
        LOGGER.info("ilk geri tiklama");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
          //      By.id("com.ozdilek.ozdilekteyim:id/ivBack"))).click();
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/ivBack")).click();
        LOGGER.info("iki defa geri click islemi");
    }
    @Step("urun bedeni varsa sec")
    public void selectProductSize() {
        Random rndSelectProduct = new Random();

        if (true) {
            List<MobileElement> selectProduct = appiumDriver.findElements(
                    By.xpath("//*[@resource-id='com.ozdilek.ozdilekteyim:id/recyclerSizeList']//android.widget.TextView"));
            int index = rndSelectProduct.nextInt(selectProduct.size());
            selectProduct.get(index).click();
        }
        LOGGER.info("urun secimi yapildi");
    }
    @Step("<key> sepete ekle")
    public void addedBasket(String key) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(
        //        By.id(key))).click();
        appiumDriver.findElement(By.id(key)).click();
        LOGGER.info("urun sepete eklendi");
    }
}
