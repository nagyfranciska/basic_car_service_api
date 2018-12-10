//package service;
//
//import com.google.inject.Inject;
//import dao.UserDAO;
//import org.mindrot.jbcrypt.BCrypt;
//import org.restlet.Request;
//import org.restlet.Response;
//import org.restlet.security.SecretVerifier;
//
//
//public class UserVerifier extends SecretVerifier {
//
//    @Inject
//    UserDAO userDAO;
//
//    public UserVerifier() {}
////
////    @Override
////    public int verify(Request request, Response response) {
////        int result = RESULT_MISSING;
////        if (request.getChallengeResponse() != null) {
////            String identifier = this.getIdentifier(request, response);
////            char[] secret = this.getSecret(request, response);
////            result = this.verify(identifier, secret);
////            if (result == RESULT_VALID) {
////                request.getClientInfo().setUser(this.createUser(identifier, request, response));
////            }
////        }
////        return result;
////    }
////
////    @Override
////    protected org.restlet.security.User createUser(String identifier, Request request, Response response) {
////        return new org.restlet.security.User(identifier);
////    }
//
//    @Override
//    public int verify(String s, char[] chars) {
//        String password = String.copyValueOf(chars);
//        String hashedPassword = userDAO.getPasswordByUsername(s);
//        if (hashedPassword == null) {
//            return RESULT_UNKNOWN;
//        } else {
//            return (BCrypt.checkpw(password, hashedPassword) ? RESULT_VALID : RESULT_INVALID);
//        }
//    }
//}
