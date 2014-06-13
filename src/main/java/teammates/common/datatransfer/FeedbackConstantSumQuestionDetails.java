package teammates.common.datatransfer;

import java.util.ArrayList;
import java.util.List;

import teammates.common.util.Const;
import teammates.common.util.FeedbackQuestionFormTemplates;

public class FeedbackConstantSumQuestionDetails extends FeedbackAbstractQuestionDetails {
    public int numOfConstSumOptions;
    public List<String> constSumOptions;
    public boolean distributeToRecipients;
    public boolean pointsPerOption;
    public int points;
    
    public FeedbackConstantSumQuestionDetails() {
        super(FeedbackQuestionType.CONSTSUM);
        
        this.numOfConstSumOptions = 0;
        this.constSumOptions = new ArrayList<String>();
        this.distributeToRecipients = false;
        this.pointsPerOption = false;
        this.points = 100;
    }

    public FeedbackConstantSumQuestionDetails(String questionText,
            int numOfConstSumOptions, List<String> constSumOptions,
            boolean pointsPerOption, int points) {
        super(FeedbackQuestionType.CONSTSUM, questionText);
        
        this.numOfConstSumOptions = constSumOptions.size();
        this.constSumOptions = constSumOptions;
        this.distributeToRecipients = false;
        this.pointsPerOption = pointsPerOption;
        this.points = points;
    }

    public FeedbackConstantSumQuestionDetails(String questionText,
            boolean pointsPerOption, int points) {
        super(FeedbackQuestionType.CONSTSUM, questionText);
        
        this.numOfConstSumOptions = 0;
        this.constSumOptions = new ArrayList<String>();
        this.distributeToRecipients = true;
        this.pointsPerOption = pointsPerOption;
        this.points = points;
    }

    @Override
    public String getQuestionTypeDisplayName() {
        if(!distributeToRecipients){
            return Const.FeedbackQuestionTypeNames.CONSTSUM_OPTION;
        } else {
            return Const.FeedbackQuestionTypeNames.CONSTSUM_RECIPIENT;    
        }
    }

    @Override
    public String getQuestionWithExistingResponseSubmissionFormHtml(
            boolean sessionIsOpen, int qnIdx, int responseIdx, String courseId,
            FeedbackAbstractResponseDetails existingResponseDetails) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getQuestionWithoutExistingResponseSubmissionFormHtml(
            boolean sessionIsOpen, int qnIdx, int responseIdx, String courseId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getQuestionSpecificEditFormHtml(int questionNumber) {
        StringBuilder optionListHtml = new StringBuilder();
        String optionFragmentTemplate = FeedbackQuestionFormTemplates.CONSTSUM_EDIT_FORM_OPTIONFRAGMENT;
        for(int i = 0; i < numOfConstSumOptions; i++) {
            String optionFragment = 
                    FeedbackQuestionFormTemplates.populateTemplate(optionFragmentTemplate,
                            "${i}", Integer.toString(i),
                            "${constSumOptionValue}", constSumOptions.get(i),
                            "${Const.ParamsNames.FEEDBACK_QUESTION_CONSTSUMOPTION}", Const.ParamsNames.FEEDBACK_QUESTION_CONSTSUMOPTION);

            optionListHtml.append(optionFragment + Const.EOL);
        }
        
        String html = FeedbackQuestionFormTemplates.populateTemplate(
                FeedbackQuestionFormTemplates.CONSTSUM_EDIT_FORM,
                "${constSumEditFormOptionFragments}", optionListHtml.toString(),
                "${questionNumber}", Integer.toString(questionNumber),
                "${Const.ParamsNames.FEEDBACK_QUESTION_NUMBEROFCHOICECREATED}", Const.ParamsNames.FEEDBACK_QUESTION_NUMBEROFCHOICECREATED,
                "${numOfConstSumOptions}", Integer.toString(numOfConstSumOptions),
                "${constSumToRecipientsValue}", (distributeToRecipients == true) ? "true" : "false",
                "${checkedConstSumPointsPerOption}", (pointsPerOption == true) ? "checked=\"checked\"" : "",
                "${constSumPoints}", (points == 0) ? "100" : new Integer(points).toString(),
                "${Const.ParamsNames.FEEDBACK_QUESTION_CONSTSUMTORECIPIENTS}", Const.ParamsNames.FEEDBACK_QUESTION_CONSTSUMTORECIPIENTS,
                "${Const.ParamsNames.FEEDBACK_QUESTION_CONSTSUMPOINTSPEROPTION}", Const.ParamsNames.FEEDBACK_QUESTION_CONSTSUMPOINTSPEROPTION,
                "${Const.ParamsNames.FEEDBACK_QUESTION_CONSTSUMPOINTS}", Const.ParamsNames.FEEDBACK_QUESTION_CONSTSUMPOINTS);
        
        return html;
    }

    @Override
    public String getQuestionAdditionalInfoHtml(int questionNumber,
            String additionalInfoId) {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public String getQuestionResultStatisticsHtml(
            List<FeedbackResponseAttributes> responses) {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public boolean isChangesRequiresResponseDeletion(
            FeedbackAbstractQuestionDetails newDetails) {
        FeedbackConstantSumQuestionDetails newConstSumDetails = (FeedbackConstantSumQuestionDetails) newDetails;

        if (this.numOfConstSumOptions != newConstSumDetails.numOfConstSumOptions ||
            this.constSumOptions.containsAll(newConstSumDetails.constSumOptions) == false ||
            newConstSumDetails.constSumOptions.containsAll(this.constSumOptions) == false) {
            return true;
        }
        
        if(this.distributeToRecipients != newConstSumDetails.distributeToRecipients) {
            return true;
        }
        
        return false;
    }

    @Override
    public String getCsvHeader() {
        return "Feedback";
    }

    @Override
    public List<String> validateQuestionDetails() {
        // TODO Auto-generated method stub
        return new ArrayList<String>();
    }

    @Override
    public List<String> validateResponseAttributes(
            List<FeedbackResponseAttributes> responses) {
        // TODO Auto-generated method stub
        return new ArrayList<String>();
    }


}
