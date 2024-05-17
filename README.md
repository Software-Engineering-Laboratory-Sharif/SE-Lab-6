# SE-Lab-6

## پیاده سازی
در این مسئله دو State و دو Transition داریم. برای State ها از الگوی State استفاده می‌کنیم و دو کلاس DeliveredState و TransitState تعریف می‌کنیم که هر یک وظیفه هندل کردن یک وضعیت از بسته را دارند. همچنین برای Transition از الگوی Stratehy استفاده می‌کنیم و دو استراتژی برای ارسال بسته در نظر داریم که هر یک در یکی از کلاس های StandardTransitionStrategy و یا ExpressTransitionStrategy هندل شده است. در کلاس Shipment کلیت ارسال یک بسته را پیاده سازی کرده‌ایم، ارسال یک بسته شامل یک استراتژی و یک استیت از بسته است. در ادامه کلاس Main و نحوه اجرای این برنامه را مشاهده می‌کنیم.
```java
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the weight of the shipment: ");
        float weight = scanner.nextFloat();
        Shipment shipment = new Shipment(weight);

        while (true) {
            System.out.print(
                    "\nChoose an option to modify: \n1. Change State\n2. Change Transition\nEnter your choice: ");
            int option = scanner.nextInt();

            if (option == 1) {
                System.out.print("Select the new state: \n1. In-transit\n2. Delivered\nEnter your choice: ");
                int stateOption = scanner.nextInt();
                if (stateOption == 1) {
                    shipment.setState(new TransitState());
                    System.out.println("The shipment is now in-transit.");
                } else if (stateOption == 2) {
                    shipment.setState(new DeliveredState());
                    System.out.println("The shipment has been delivered.");
                    break;
                } else {
                    System.out.println("Invalid state choice. Please try again.");
                    continue;
                }
                System.out.println("Current state: " + shipment.getState());
            } else if (option == 2) {
                System.out.print("Select the transition type: \n1. Standard\n2. Express\nEnter your choice: ");
                int transitionOption = scanner.nextInt();
                if (transitionOption == 1) {
                    shipment.setTransitionStrategy(new StandardTransitionStrategy());
                    System.out.println("The shipment is now using standard transition.");
                } else if (transitionOption == 2) {
                    shipment.setTransitionStrategy(new ExpressTransitionStrategy());
                    System.out.println("The shipment is now using express transition.");
                } else {
                    System.out.println("Invalid transition choice. Please try again.");
                    continue;
                }
                System.out.println("Transition type changed. The new cost is $" + shipment.executeTransition());
            } else {
                System.out.println("Invalid option. Please enter either 1 or 2.");
            }
        }
    }
}

```   

## اجرای برنامه
‍‍‍![image](https://github.com/Software-Engineering-Laboratory-Sharif/SE-Lab-6/assets/79264950/f03314b5-acae-4372-a8cf-a955ecbad625)



## پرسش‌ها

1. در کتاب GoF سه دسته الگوی طراحی معرفی شده است. آن‌ها را نام ببرید و در مورد هر دسته در حد دو خط توضیح دهید.
- الگوهای طراحی سازنده (Creational Design Patterns): این الگوها الگوهای طراحی هستند که با مکانیسم های ایجاد اشیا سروکار دارند و سعی می کنند اشیا را به شیوه ای متناسب با موقعیت ایجاد کنند. شکل اصلی ایجاد شی می تواند منجر به مشکلات طراحی یا افزودن پیچیدگی به طراحی شود. الگوهای طراحی سازنده این مشکل را با کنترل ایجاد این شیء حل می کنند.
- الگوهای طراحی ساختاری (Structural Design Patterns): این نوع الگو طرحی است از نحوه ترکیب اشیاء و کلاس‌های مختلف برای تشکیل ساختار بزرگ‌تری برای دستیابی به اهداف متعدد. این الگوها نشان می‌دهند که چگونه قطعات منحصربه‌فرد یک سیستم را می‌توان به شکلی قابل توسعه و انعطاف‌پذیر با هم ترکیب کرد. بنابراین، با کمک این الگوها می‌توانیم بخش‌های خاصی از ساختار را بدون تغییر کل ساختار مورد هدف قرار داده و تغییر دهیم.
- الگوهای طراحی رفتاری (Behavioral Design Patterns): این دسته، الگوهای ارتباطی مشترک بین اشیاء را شناسایی کرده و این الگوها را تحقق می بخشند. با انجام این کار، این الگوها انعطاف پذیری را در انجام این ارتباط افزایش می دهند.
2. الگوهای استفاده شده در این آزمایش جزو کدام دسته هستند؟
الگوهای طراحی رفتاری
3. با توجه به این که در هر اجرا محصرا یک بسته داریم و هیچ بسته‌ی دیگری بجز آن نداریم، کدام الگوی طراحی را برای ایحاد آن مناسب می‌دانید؟ ضمن بیان علت انتخاب خود، نحوه تحقق الگو را به طور کامل توضیح دهید.
   برای حل این مشکل می‌توان از الگوی Singleton استفاده کرد. در این الگو می‌ةوان اطمینان حاصل کرد که فقط یک instance از بسته در کد موجود است و instance جدیدی از آن ساخته نمی‌شود.
  نحوه پیاده سازی این الگو بدین شکل است.
  ```java
public class Shipment {
    private static Shipment shipment;
    private State state;
    private TransitionStrategy transitionStrategy;
    private final float weight;

    private Shipment(float weight) {
        this.weight = weight;
    }

    public static Shipment getInstance(float weight) {
        if (shipment == null) {
            shipment = new Shipment(weight);
        }
        return shipment;
    }
}
  ```
4. تحقق و یا عدم تحقق هر کدام از اصول SOLID را در خصوص الگوی طراحی Singleton بیان کنید (هرکدام حداکثر در سه خط).
  
  - اصل SRP: این اصل مسئولیت واحد را نقض می کند. این الگو دو مشکل را حل می کند (اطمینان از اینکه یک کلاس فقط یک نمونه دارد و ارائه تنها یک مکان برای دسترسی به آن).
  - اصل OCP: برای اینکه یک کلاس open باشد، باید امکان ارث بردن از آن وجود داشته باشد. وراثت یک رابطه "is-a" است. اگر از یک کلاس singleton به ارث می برید، نمونه هایی از کلاس فرزند نیز به دلیل رابطه "is-a" نمونه هایی از کلاس والد هستند، به این معنی که می توانید ناگهان چندین نمونه از کلاس singleton داشته باشید. اگر کلاس singleton از وراثت جلوگیری کند، دیگر open نیست. اگر یک کلاس singleton اجازه وراثت را بدهد، و برای گسترش open باشد، دیگر نمی تواند الگوی singleton را اجرا کند.
  - اصل LSP: خود الگوی Singleton ذاتاً اصل جایگزینی Liskov را نقض نمی کند. با این حال، پیاده سازی الگوی Singleton گاهی اوقات می تواند منجر به مسائلی شود که ممکن است به طور غیرمستقیم با LSP در تضاد باشد، اگر با دقت طراحی نشود (مانند مورد قبلی).
  - اصل ISP: الگوی Singleton خود ذاتاً اصل جداسازی رابط را نقض نمی کند (اگر رابط طراحی ضعیفی داشته باشد، ممکن است نقض شود). بنابراین می‌توانیم یک پیاده‌سازی اینترفیس Singleton داشته باشیم که تنها دارای یک تابع به نام getInstance در کنار برخی از اینترفیس‌های دیگر است که سعی در پیاده‌سازی برخی ویژگی‌های دلخواه دارند.
  - اصل DIP: لزوماً اصل وارونگی وابستگی را نقض نمی کند، اما مانند مورد آخر، اگر طراحی ضعیفی داشته باشد، می تواند نقض شود. در واقع الگوی طراحی Singleton هیچ پیچیدگی و هیچ تلاقی با نحوه ارتباط کلاس های پیاده سازی شده با کلاس های دیگر ندارد.
