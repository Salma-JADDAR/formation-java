package modules;

public abstract class Personne {
    protected String nom;
    protected String prenom;
    protected String email;
    protected  String motDepass;
    protected char genre;

    public Personne(String nom,String prenom,String email,String motDepass,char genre){
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.motDepass=motDepass;
        this.genre=genre;
    }

    public String getNom(){
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDepass(){
        return motDepass;
    }

    public char getGenre(){
        return genre;
    }

    public void setNom( String nom){
        this.nom=nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDepass(String motDepass) {
        this.motDepass = motDepass;
    }

    public void afficher_Mes_Info_Personnels(){
        System.out.println("Mon Nom est :" +getNom()+"et mon Prenom est :"+getPrenom()+"et mon email est :"+getEmail());
    }


    public void bienvenu(String motDepass,String emaileTech){
        if(this.genre=='F' && authentifier(motDepass,emaileTech) ){
            System.out.println("Bienvenu Madame"+getNom()+" dans votre espace en notre site NexaBank");
        }else{
            System.out.println("Bienvenu Monsieur"+getMotDepass()+" dans votre espace en notre site NexaBank");
        }
    }

    public Boolean authentifier(String name,String emaileTech){
        if(name.equals(getNom()) && emaileTech.equals(getEmail())){
            return true;
        }
        return false;
    }
}
