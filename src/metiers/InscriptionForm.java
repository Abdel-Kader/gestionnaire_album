package metiers;

import dao.ExceptionDao;
import dao.UserDao;
import entities.TypeUtilisateur;
import entities.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InscriptionForm
{
    private static final String CHAMP_NOM                           = "nom";
    private static final String CHAMP_PRENOM                        = "prenom";
    private static final String CHAMP_LOGIN                         = "login";
    private static final String CHAMP_TELEPHONE                     = "telephone";
    private static final String CHAMP_PASSWORD                      = "password";
    private static final String CHAMP_PASSWORD_CONFIRMATION         = "password_confirmation";
    private static final String REQ         = "req";
    private static final String TYPE_USER         = "type_user";

    private static final String CHAMP_FORM = "form";

    private String              resultat;
    private Map<String, String> erreurs          = new HashMap<>();
    private UserDao      userDao;

    public InscriptionForm(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Utilisateur register(HttpServletRequest request)
    {
        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String login = getValeurChamp( request, CHAMP_LOGIN);
        String telephone = getValeurChamp( request, CHAMP_TELEPHONE);
        String password = getValeurChamp( request, CHAMP_PASSWORD);
        String password_confirmation = getValeurChamp( request, CHAMP_PASSWORD_CONFIRMATION);
        String req = getValeurChamp( request, REQ);


        Utilisateur user = new Utilisateur();

        try
        {
            traiterLogin(login, user);
            traiterNom(nom, user);
            traiterPrenom(prenom, user);
            traiterTelephone(telephone, user);
            if(req.equals("user"))
            {
                traiterMotsDePasse(password,password_confirmation, user);
                user.setBy_admin(false);
                TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
                typeUtilisateur.setId((long) 2);
                user.setBy_admin(false);
                user.setType_utilisateur(typeUtilisateur);
            }
            else
            {
                Long type_user = Long.parseLong(getValeurChamp( request, TYPE_USER ));
                TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
                typeUtilisateur.setId(type_user);
                user.setType_utilisateur(typeUtilisateur);
                user.setPassword("dddddfjfj");
                user.setBy_admin(true);
            }

            if(erreurs.isEmpty())
            {
                userDao.ajouter(user);
                resultat = "Inscription effectuée avec succès";
            }
            else
            {
                resultat = "Echechc de l'inscription !";
            }
        }
        catch (ExceptionDao e)
        {
            resultat = "Une erreur s'est produite lors de l'inscription !";
            e.printStackTrace();
        }

        return user;
    }


    private void traiterLogin( String login, Utilisateur utilisateur )
    {
        try
        {
            validationLogin( login );
        }
        catch ( ExceptionDao e )
        {
            setErreur( CHAMP_LOGIN, e.getMessage() );
        }
        utilisateur.setLogin( login );
    }

    private void traiterLog( String login, Utilisateur utilisateur )
    {
        try
        {
            validationLog( login );
        }
        catch ( ExceptionDao e )
        {
            setErreur( CHAMP_LOGIN, e.getMessage() );
        }
        utilisateur.setLogin( login );
    }

    private void traiterMotDePasse(String password, Utilisateur utilisateur)
    {
        try
        {
            validationMotDePasse(password);
        } catch (ExceptionDao e)
        {
            setErreur(CHAMP_PASSWORD, e.getMessage());
        }
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        byte[]hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        //bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        utilisateur.setPassword( sb.toString() );
    }

    private void traiterMotsDePasse( String password, String password_confirmation, Utilisateur utilisateur) {

            try {
                validationMotsDePasse( password, password_confirmation );
            } catch ( ExceptionDao e ) {
                setErreur( CHAMP_PASSWORD, "password" );
                setErreur( CHAMP_PASSWORD_CONFIRMATION, e.getMessage() );
            }
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        byte[]hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        //bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }

            utilisateur.setPassword( sb.toString() );

    }


    public Utilisateur userLogin(HttpServletRequest request)
    {
        String login = getValeurChamp(request, CHAMP_LOGIN);
        String password = getValeurChamp(request, CHAMP_PASSWORD);

        Utilisateur user = new Utilisateur();
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        byte[]hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        //bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        try
        {
            traiterLog(login, user);
            traiterMotDePasse(password, user);

            if (erreurs.isEmpty())
            {
                if(userDao.userLogin(login, sb.toString()) == null)
                {
                    setErreur(CHAMP_FORM, "Login ou mot de passe incorrects !");
                    user = null;
                }
                else
                {
                    resultat = "Inscription effectuée avec succès";
                    user = userDao.userLogin(login, sb.toString());
                }
            }
            else
            {
                resultat = "Echechc de l'inscription !";
            }
        } catch (ExceptionDao e)
        {
            resultat = "Une erreur s'est produite lors de l'inscription !";
            e.printStackTrace();
        }
        return user;

    }

    private void validationMotDePasse( String password ) throws ExceptionDao {
        if ( password != null ) {
            if ( password.length() < 3 )
            {
                throw new ExceptionDao( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new ExceptionDao( "Merci de saisir votre mot de passe." );
        }
    }

    public List<Utilisateur> getUsers()
    {
        return userDao.findAllUsers();
    }

    public List<Utilisateur> findUsers()
    {
        return userDao.findUsers();
    }

    public List<Utilisateur> findAdmins()
    {
        return userDao.findAdmin();
    }

    public Utilisateur editUser(Utilisateur userToUpdate)
    {
        return userDao.editUser(userToUpdate);
    }


    private void traiterNom( String nom, Utilisateur utilisateur ) {
        try {
            validationNom( nom );
        } catch ( ExceptionDao e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        utilisateur.setNom( nom );
    }

    private void traiterPrenom( String prenom, Utilisateur utilisateur ) {
        try {
            validationNom( prenom );
        } catch ( ExceptionDao e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        utilisateur.setPrenom( prenom );
    }

    private void traiterTelephone( String telephone, Utilisateur utilisateur ) {
        try {
            validationTelephone( telephone );
        } catch ( ExceptionDao e ) {
            setErreur( CHAMP_TELEPHONE, e.getMessage() );
        }
        utilisateur.setContact( telephone );
    }

    /* Validation des mots de passe */
    private void validationMotsDePasse( String password, String password_confirmation ) throws ExceptionDao {
        if ( password != null && password_confirmation != null ) {
            if (!(password.equals( password_confirmation ))) {
                throw new ExceptionDao( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( password.length() < 3 ) {
                throw new ExceptionDao( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new ExceptionDao( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    /* Validation du nom */
    private void validationNom( String nom ) throws ExceptionDao {
        if ( nom != null && nom.length() < 3 ) {
            throw new ExceptionDao( "Ce champ doit contenir au moins 3 caractères." );
        }
    }

    private void validationTelephone( String telephone ) throws ExceptionDao {
        if ( telephone != null  )
        {
//            Pattern pattern = Pattern.compile("d{9}");
//            Matcher matcher = pattern.matcher(telephone);
//
//            if (matcher.matches())
//            {
//                System.out.println("uuuu");
//            }
//            else
//            {
//                throw new ExceptionDao( "Le numéro de téléphone n'est pas valide ! Il doit contenir 9 Chiffres" );
//            }

        }
        else
        {
            throw new ExceptionDao( "Merci de saisir votre numéro de téléphone." );
        }
    }

    private void validationLog( String login ) throws ExceptionDao
    {
        if (login == null)
        {
            throw new ExceptionDao("Ce champ est obligatoire. Merci de le remplir");
        }
    }

    private void validationLogin( String login ) throws ExceptionDao {
        if ( login != null ) {
            if ( userDao.getLogin( login ) != null )
            {
                throw new ExceptionDao( "Ce nom d'utilisateur est déjà utilisé, merci d'en choisir une autre !" );
            }
        } else {
            throw new ExceptionDao( "Ce champ est obligatoire. Merci de le remplir" );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 )
        {
            return null;
        } else {
            return valeur;
        }
    }


    public Utilisateur findById(Long id)
    {
        return userDao.findById(id);
    }

    public void deleteUser(Utilisateur user)
    {
        userDao.deleteUser(user);
    }

    public Long CountAllUser()
    {
        return userDao.CountAllUser();
    }

    public Long CountAllAdmin()
    {
        return userDao.CountAllAdmin();
    }


}
