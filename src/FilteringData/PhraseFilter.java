package FilteringData;

public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    
    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        // TODO Auto-generated method stub
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        String currPhrase = "";

        try {
            String currAnyPhase = qe.getInfo();
            String currStartPhase = qe.getInfo().substring(0, qe.getInfo().indexOf(","));
            String currEndPhase = qe.getInfo().substring(qe.getInfo().indexOf(",")+2);

            if (where.equals("start")) {
                currPhrase = currStartPhase;
            } else if (where.equals("end")) {
                currPhrase = currEndPhase;
            } else {
                currPhrase = currAnyPhase;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        if (currPhrase.contains(phrase)) {
            return true;
        } else {
            return false;
        }               
    }    
}
