package importantQuestions;

import java.util.*;
/*
 You are a librarian at a public library, and you've been asked to write a program that will sort the library's books by genre in ascending order. The program should take in a list of books, where each book is having a "title", "author", and "genre". You have given N book details, and your task sorts it in ascending order of genre and returns the title of the book in a string array.

Input Format

The first line contains an integer N, denoting the total number of books.
Next N lines contain a string that denotes "title", "author", and "genre" separated by a comma.
Note: In code stub, you get 2D array books: where the 0th column denotes the title, the 1st column denotes the author, and the 2nd column denotes the genre.
Constraints

1<=N<=100
1<=|title|, |author|, and |genre|<=20
Output Format

Return string array of titles.
Note: If 2 genres are the same then title must be in the same order as they appear in the input array.
Evaluation Parameters

Sample Input

4
The Great Gatsby,F. Scott Fitzgerald,fiction
The Diary of a Young Girl,Anne Frank,non-fiction
Harry Potter and the Philosophers Stone,J.K. Rowling,children
The Catcher in the Rye,J.D. Salinger,fiction
Sample Output

Harry Potter and the Philosophers Stone
The Great Gatsby
The Catcher in the Rye
The Diary of a Young Girl
 */
class Book {
    String title;
    String author;
    String genre;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}

public class LibrarianSort {
    public static String[] sortBooksByGenre(List<List<String>> books) {

        List<Book> bookList = new ArrayList<>();

        for (List<String> bookDetails : books) {
            String title = bookDetails.get(0);
            String author = bookDetails.get(1);
            String genre = bookDetails.get(2);
            bookList.add(new Book(title, author, genre));
        }
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).genre);
        }
        Collections.sort(bookList, (book1, book2) -> {
            int genreComparison = book1.genre.compareTo(book2.genre);
            if (genreComparison == 0) {
                return 0; // Maintain the original order if genres are the same
            } else {
                return genreComparison;
            }
        });
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).genre);
        }
        String[] sortedTitles = new String[bookList.size()];
        for (int i = 0; i < bookList.size(); i++) {
            sortedTitles[i] = bookList.get(i).title;
        }

        return sortedTitles;
    }

    public static void main(String[] args) {
        List<List<String>> books = new ArrayList<>();
        books.add(Arrays.asList("The Great Gatsby", "F. Scott Fitzgerald", "fiction"));
        books.add(Arrays.asList("The Diary of a Young Girl", "Anne Frank", "non-fiction"));
        books.add(Arrays.asList("Harry Potter and the Philosophers Stone", "J.K. Rowling", "children"));
        books.add(Arrays.asList("The Catcher in the Rye", "J.D. Salinger", "fiction"));

        String[] sortedTitles = sortBooksByGenre(books);

        for (String title : sortedTitles) {
            System.out.println(title);
        }
    }
}
