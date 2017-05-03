package rml.dao;

import org.springframework.stereotype.Service;
import rml.model.RegisterUserModle;

/**
 * Created by BBLink on 2017/5/3.
 */
@Service("userMapping")
public interface UserMapper {
    //注册用户
    int registerUser(RegisterUserModle registerUserModle);


}
