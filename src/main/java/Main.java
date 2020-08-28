import bl.HibernateUtil;
import org.hibernate.Session;
import entities.Role;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.RoleService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        HibernateUtil.buildSessionFactory();
        Scanner scanner = new Scanner(System.in);

        RoleService roleService = new RoleService();
        String choice = "0";
        do {
            System.out.println(" ... MENU ...");
            System.out.println("1 - Create role");
            System.out.println("2 - Read role by ID");
            System.out.println("3 - Update role");
            System.out.println("4 - Delete role");
            System.out.println("5 - Read all roles");
            System.out.println("0 - Exit ");
            System.out.print("Enter your choice: ");

            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("--> Adding:");
                    System.out.print("Enter new role");
                    String nameRole = scanner.nextLine();
                    Role role = new Role(nameRole);
                    roleService.add(role);
                    System.out.println("Role added");
                    break;
                case "2":
                    System.out.println("--> Showing role by ID");
                    System.out.print("Enter ID of role: ");
                    int id;
                    Role roleById;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                        roleById = roleService.getById(id);
                    } catch (Exception e) {
                        roleById = null;
                    }

                    if (roleById != null) {
                        System.out.println("ROLE: [" + roleById.getName() + "] " + "ID: " + roleById.getId());
                    } else
                        System.out.println("not found");
                    break;
                case "3":
                    System.out.println("--> Updating role");
                    System.out.print("Enter ID of role: ");
                    int idUpdate = 0;
                    Role roleByIdUpdate;
                    try {
                        idUpdate = Integer.parseInt(scanner.nextLine());
                        roleByIdUpdate = roleService.getById(idUpdate);
                    } catch (Exception e) {
                        roleByIdUpdate = null;
                    }

                    if (roleByIdUpdate != null) {
                        System.out.println("Enter new name for role with id " + idUpdate);
                        String nameUpdate = scanner.nextLine();
                        roleByIdUpdate.setName(nameUpdate);
                        roleService.update(roleByIdUpdate);
                        System.out.println("ROLE: [" + roleByIdUpdate.getName() + "] " + "ID: " + roleByIdUpdate.getId() + " updated");
                    } else
                        System.out.println("not found");
                    break;
                case "4":
                    System.out.println("--> Deleting role by ID");
                    System.out.print("Enter ID of role: ");
                    int idDelete;
                    Role roleByIdDelete;
                    try {
                        idDelete = Integer.parseInt(scanner.nextLine());
                        roleByIdDelete = roleService.getById(idDelete);
                    } catch (Exception e) {
                        roleByIdDelete = null;
                    }

                    if (roleByIdDelete != null) {
                        System.out.println("ROLE: [" + roleByIdDelete.getName() + "] " + "ID: " + roleByIdDelete.getId() + " deleted");
                        roleService.remove(roleByIdDelete);
                    } else
                        System.out.println("not found");
                    break;
                case "5":
                    List<Role> roleList = roleService.getAll();
                    System.out.println();

                    for (Role item : roleList) {
                        System.out.println("ROLE: [" + item.getName() + "] " + "ID: " + item.getId());
                    }
                    break;
                default:
                    break;
            }

            if (!choice.equals("0")) {
                System.out.print("\nPress any key ... ");
                scanner.nextLine();
            }

        } while (!choice.equals("0"));
        HibernateUtil.shutdown();
    }
}
