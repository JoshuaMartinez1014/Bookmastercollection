package rasmussen.bookmasters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;


public class Books {
	
	private Connection conn = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public void getAllBooks() { 
		try {
			// connection line to MySql
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bookmasterscollection","root","Josh1234");
			st = conn.createStatement();
			rs = st.executeQuery("select * from books");
			
			// loop through data received
			while (rs.next()) {
				
                int bookId = rs.getInt("book_id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                
                // Print the data to the console
                System.out.println("---------------");
                System.out.println("Book ID: " + bookId);
                System.out.println("Name: " + name);
                System.out.println("Quantity: " + quantity);
                System.out.println("Price: " + price);
                System.out.println("Category: " + category);
                
                if (quantity <= 5) {
                	final String ANSI_RED = "\u001B[31m";
                    final String ANSI_RESET = "\u001B[0m"; // Resets the color

                    System.out.println(ANSI_RED + "Alert: Low inventory for '" + name + "'. Only " + quantity + " left in stock." + ANSI_RESET);
                }

                System.out.println();    
              
			}
			
		} catch (SQLException e) {	
			System.out.println("error grabbing data");
			e.printStackTrace();
		}finally {
            // Close resources in a finally block to ensure they are always closed
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            	System.out.println("error closing recourses");
                e.printStackTrace();
            }
        }
	}
	
	
	
	public void searchForBook(String bookTitle) {
		LoggerUtility.log(Level.INFO, "searchForBook method called for booktitle: " + bookTitle);
		try {
			// connection line to MySql
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bookmasterscollection","root","Josh1234");
			st = conn.createStatement();
			rs = st.executeQuery("select * from books where name like '%" + bookTitle + "%'");
			
			// check if book was found
			boolean found = false; 
			
			// loop through data received
			while (rs.next()) {
				found = true;
                int bookId = rs.getInt("book_id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                
                // Print the data to the console
            	System.out.println("---------------");
                System.out.println("Book ID: " + bookId);
                System.out.println("Name: " + name);
                System.out.println("Quantity: " + quantity);
                System.out.println("Price: " + price);
                System.out.println("Category: " + category);
                System.out.println();
                
                if (quantity <= 5) {
                	final String ANSI_RED = "\u001B[31m";
                    final String ANSI_RESET = "\u001B[0m"; // Resets the color

                    System.out.println(ANSI_RED + "Alert: Low inventory for '" + name + "'. Only " + quantity + " left in stock." + ANSI_RESET);
                }

                System.out.println();    
			}
			
			// If book isnt found
			if (!found) {
				System.out.println("*********************");
				 System.out.println("no results found");
				 System.out.println("*********************");
			}
			
		} catch (SQLException e) {	
			System.out.println("error grabbing data");
			LoggerUtility.log(Level.SEVERE, "error grabbing data: " + e.getMessage());
			e.printStackTrace();
		}finally {
            // Close resources in a finally block to ensure they are always closed
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            	System.out.println("error closing recourses");
                e.printStackTrace();
            }
        }
	}
	
	public void addBook(String bookTitle, String bookQuantity, String bookPrice, String bookCategory) {
		LoggerUtility.log(Level.INFO, "addBook method called for book: " + "bookTitle: " + bookTitle 
				+ "bookQuantity: " + bookQuantity + "bookPrice: " + bookPrice + "bookCategory: " + bookCategory);
		try {
			// connection line to MySql
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bookmasterscollection","root","Josh1234");	
			String sql = "INSERT INTO books (name, quantity, price, category) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, bookTitle);
	        ps.setInt(2, Integer.parseInt(bookQuantity));
	        ps.setDouble(3, Double.parseDouble(bookPrice));
	        ps.setString(4, bookCategory);
	        
	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Book added successfully");
	        } else {
	            System.out.println("Error inserting book");
	        } } catch (SQLException e) {  
	            System.out.println("Error executing SQL statement");
	            LoggerUtility.log(Level.SEVERE, "Error executing SQL statement: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            // Close resources
	            try {
	                if (ps != null) ps.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                System.out.println("Error closing resources");
	                e.printStackTrace();
	            }
	        }
	    }
	
	
	public void searchForCategory(String bookCategory) {
		LoggerUtility.log(Level.INFO, "searchForCategory method called for bookCategory: " + bookCategory);
		try {
			// connection line to MySql
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bookmasterscollection","root","Josh1234");
			st = conn.createStatement();
			rs = st.executeQuery("select * from books where category like '%" + bookCategory + "%'");
			
			// check if book was found
			boolean found = false; 
			
			// loop through data received
			while (rs.next()) {
				found = true;
                int bookId = rs.getInt("book_id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                
                // Print the data to the console
                System.out.println("---------------");
                System.out.println("Book ID: " + bookId);
                System.out.println("Name: " + name);
                System.out.println("Quantity: " + quantity);
                System.out.println("Price: " + price);
                System.out.println("Category: " + category);
                
                if (quantity <= 5) {
                	final String ANSI_RED = "\u001B[31m";
                    final String ANSI_RESET = "\u001B[0m"; // Resets the color

                    System.out.println(ANSI_RED + "Alert: Low inventory for '" + name + "'. Only " + quantity + " left in stock." + ANSI_RESET);
                }

                System.out.println();    
			}
			
			// If book isnt found
			if (!found) {
				System.out.println("*********************");
				 System.out.println("no results found");
				 System.out.println("*********************");
			}
			
		} catch (SQLException e) {	
			System.out.println("error grabbing data");
			LoggerUtility.log(Level.SEVERE, "error grabbing data: " + e.getMessage());
			e.printStackTrace();
		}finally {
            // Close resources in a finally block to ensure they are always closed
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            	System.out.println("error closing recourses");
                e.printStackTrace();
            }
        }
	}
	
	public void removeBook(String bookRemoveTitle) {
		LoggerUtility.log(Level.INFO, "removeBook method called for bookRemoveTitle: " + bookRemoveTitle);
		try {
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/bookmasterscollection", "root", "Josh1234");
	        String sql = "DELETE FROM books WHERE name = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, bookRemoveTitle);

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Book removed successfully.");
	        } else {
	            System.out.println("No book found with title: " + bookRemoveTitle);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error executing SQL statement.");
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            System.out.println("Error closing resources.");
	            e.printStackTrace();
	        }
	    }
	}
	
	public void updateBook(String bookUpdateTitle, String newQuantity, String newPrice, String newCategory) {
		LoggerUtility.log(Level.INFO, "removeBook method called for bookRemoveTitle: " + bookUpdateTitle);
		  try {
		        conn = DriverManager.getConnection("jdbc:mysql://localhost/bookmasterscollection", "root", "Josh1234");

		        String sql = "UPDATE books SET quantity = ?, price = ?, category = ? WHERE name = ?";
		        ps = conn.prepareStatement(sql);

		        // Setting the new values for the book
		        ps.setString(1, newQuantity);
		        ps.setString(2, newPrice);
		        ps.setString(3, newCategory);
		        ps.setString(4, bookUpdateTitle); // The book title to find the book to be updated

		        int rowsAffected = ps.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Book updated successfully.");
		        } else {
		            System.out.println("No book found with the title: " + bookUpdateTitle);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error executing SQL statement.");
		        e.printStackTrace();
		    } finally {
		        try {
		            if (ps != null) ps.close();
		            if (conn != null) conn.close();
		        } catch (SQLException e) {
		            System.out.println("Error closing resources.");
		            e.printStackTrace();
		        }
		    }
	}
	

}
