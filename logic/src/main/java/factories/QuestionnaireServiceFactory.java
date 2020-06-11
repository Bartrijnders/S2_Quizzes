package factories;

import sevices.QuestionnaireGetAllByUserService;

import java.sql.SQLException;

public class QuestionnaireServiceFactory implements Factory<QuestionnaireGetAllByUserService> {
    @Override
    public QuestionnaireGetAllByUserService Create() throws SQLException {
        return new QuestionnaireGetAllByUserService();
    }
}
