import javax.swing.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.awt.Desktop;
import java.io.IOException;

public class Share 
{

    private JTextArea textArea;
    public Share(JTextArea textArea) 
    {
        this.textArea = textArea;
    }
    public void mail()
    {
        Object[] options = {"Gmail", "Default", "Cancel"};
        String subject = "Sharing RetroPad text";
        String body = textArea.getText();
        String link;
        int what = JOptionPane.showConfirmDialog(null, "Continue?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_CANCEL_OPTION);
        if(what == JOptionPane.YES_OPTION)
        {
            int choice = JOptionPane.showOptionDialog(null, "Choose preferred mail server", "Custom Mail Server", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);                      
            switch (choice)
            {
                case 0:
                {
                    link = "https://mail.google.com/mail/u/0/?fs=1&to&su=" + URLEncoder.encode(subject, StandardCharsets.UTF_8) + "&body=" + URLEncoder.encode(body, StandardCharsets.UTF_8) + "&tf=cm";
                    System.out.println(link);
                    mailing(link);
                    break;
                }
                case 1:
                {
                    link = "mailto:?subject=" + subject.replaceAll(" ", "%20") + "&body=" + body.replaceAll(" ", "%20");
                    mailing(link);
                    break;
                }
                case 2: { break; }
                default: { break; }
            }
        }
        else{
        }
    }
    public void whatsapp()
    {
        String url = "https://wa.me?text=";
        String subject = textArea.getText();
        url += URLEncoder.encode(subject, StandardCharsets.UTF_8);
        mailing(url);
    }
    public void mailing(String url)
    {
        if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
        {
            try
            {
                Desktop.getDesktop().browse(new URI(url));
            } 
            catch (IOException | URISyntaxException e2)
            {
                JOptionPane.showMessageDialog(null, "Retry");
            }
        }
        else
        {
            try
        {
            Desktop.getDesktop().browse(new URI(url));
        } 
        catch (IOException | URISyntaxException e2)
        {
            JOptionPane.showMessageDialog(null, "Retry");
        }
        }
    }
}
