
// if we have a html document where we want to check if all the tags are in their right place

import java.util.*;

public class lab5_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("vnesi");
        String vnesi = input.nextLine(); // zima se do ENTER i ti gi smestuva vo vnesi
        Stack<String> stack = new Stack<>();
        boolean validen = true;

        String[] niza = vnesi.split("<"); // gi deli na substring site shto imaat <, "<h1><a>" -> "h1>", "a>"

        for (String part : niza) {// za sekoe parche na nizata
            String tag = otstrani(part); // SEGA SME h1; IMA .trim(), tag = h1a/a/h1 sakame
            if (tag.isEmpty()) { // ako vneseme <> i taka kje ni vrati "" aka prazen tag
                continue;
            }
            System.out.println("Tagot e najden: " + tag);
            if (tag.startsWith("/")) { // dali e kraen tag
                String closeTag = tag.substring(1); // da ni go trgne "/a" -> "a"
                if (!stack.isEmpty()) {
                    String lastTag = stack.pop(); //
                    System.out.println("Sporeduvame " + lastTag + " so " + closeTag);
                    if (!lastTag.equals(closeTag)) { // ako ne se ednakvi
                        validen = false;
                        System.out.println("Greshka vo kodot, ne odgovaraat tagsot!");
                        break;
                    } else {
                        validen = true;
                    }

                } else { // dokolku e empty
                    validen = false;
                    System.out.println("Greshka vo kodot, ne odgovaraat tagsot!");
                }

            } else { // ako se prvite tags
                stack.push(tag);
                System.out.println("Dodaden e tagot vo magacin: " + tag);
            }
        }

        if (!stack.isEmpty()) { // mora na kraj magacinot da ni e prazen za da e tochno, dokolku ne e prazen ->
                                // greshka
            validen = false;
            System.out.println("Greshka vo kodot, ne odgovaraat tagsot!");
        }
        if (validen) {
            System.out.println("Kodot e validen!");
        }

        input.close();
    }

    private static String otstrani(String part) { // mu prakjame "h1>" ni vrakja "h1"
        String result = "";
        for (int i = 0; i < part.length(); i++) {
            if (part.charAt(i) == '>')
                break; // nema da go smestive vo result
            result += part.charAt(i);

        }
        return result;
    }
}
