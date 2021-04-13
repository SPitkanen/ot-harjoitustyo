
package wbapp.domain;

/**
 *
 * @author santeripitkanen
 */
public class User {
    private Integer userId;
    private String userName;
    
    public User(int id, String name) {
        this.userId = userId;
        this.userName = name;
    }
    
    public Integer getUserId() {
        return this.userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
}
