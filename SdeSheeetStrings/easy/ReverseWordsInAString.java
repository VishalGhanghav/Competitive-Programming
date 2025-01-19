package SdeSheeetStrings.easy;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        //we will spit the string by spaces.The regex handles even more then one space
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        //Now move from last to first word.
        for(int i=words.length-1;i>0;i--){
            sb.append(words[i]+" ");
        }
        //for first word in input(last in result) i dont want to append space
        sb.append(words[0]);
        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseWordsInAString reverseWordsInAString=new ReverseWordsInAString();
        System.out.println(reverseWordsInAString.reverseWords("the sky is blue"));
        System.out.println(reverseWordsInAString.reverseWords("  hello world  "));
        System.out.println(reverseWordsInAString.reverseWords("a good   example"));
    }
}
