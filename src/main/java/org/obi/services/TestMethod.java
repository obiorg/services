/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;

/**
 *
 * @author admin.scada
 */
public class TestMethod {

    public static void main(String[] arg) {
        /**
         * -	Nous souhaitons réduire la taille des fichiers d’enregistrement à
         * 1MB -	Ce paramètre (1 MB) doit être configurable au travers de
         * l’interface (settingFrame) -	Ce paramètre doit être enregistré dans
         * le fichier de configuration accompagnant l’application (OBI.ini ou
         * obi_service.ini – je ne sais plus), NB : core dispose d’un outil
         * simple permettant d’écrire/Lire des paramètres de configuration (voir
         * comment cela est fait pour la société et le gmt ou encore les
         * liaisons au base de donnée). -	Si ce paramètre est à zéro, il ne faut
         * considérer aucun transfert de logs
         *
         * -	Un autre paramètre est définit permettant de déterminer la durée de
         * sauvegarde des logs dans sous répertoire « logs », -	Ce paramètre par
         * défaut à 7, doit être configurable au travers de l’interface
         * (SettingFrame), -	Ce paramètre doit être enregistré dans le fiche de
         * configuration accompagnant l’application -	Si ce paramètre est à
         * zéro, il ne faut considérer aucune suppression des logs * NB : Ces
         * paramètres qui précèdes sont obtenu à l’aide d’un spinner et doivent
         * être configuré pour empêcher d’entrer des paramètre aberrant (valeur
         * négative)
         *
         * -	Le processus se déroule comme suit : o	Analyse cyclique de la
         * taille du log à chaque écriture dans celui=ci o Déplacement du
         * fichier en cours dans un sous répertoire « logs » si condition ok, o
         * Crée un nouveau fichier « obiLog » pour les nouveaux enregistrement -
         * sinon sera automatiquement créé avec la prochaine sortie, o	Poursuit
         * en renommant le fichier déplacé en ajoutant le suffixe de date au
         * format « _YYYYMMJJ_HHMMSS » Ex : obiLog_20240227_213205.txt o	Si un
         * nouveau déplacement est opéré alors check de destruction d’historique
         * de log en fonction des condition ci-dessus
         *
         *
         */

        Settings.iniFilename = "obi_service.ini";
        Util.out("Settings initFilenmae = " + Settings.iniFilename);

        long logMaxSizeMB = Long.valueOf(
                Settings.read(Settings.CONFIG, Settings.LOG_FILE_SIZE_MB).toString()); // a récupérer de puis obi_service.ini

        Util.out("Taille des log max = " + logMaxSizeMB);

        File f = new File(Util.ISLogFilenamePath);
        if (f.length() >= logMaxSizeMB * 1000000 && logMaxSizeMB > 0) {
            Util.out("Taille du fichier dépasse la limite de " + logMaxSizeMB * 1000000 + " octet");
            // Recover storage backup logs dir
            String logFileStoragePath
                    = Settings.read(Settings.CONFIG, Settings.LOG_FILE_STORAGE_PATH).toString();
            // Rename file before moving
            String logNewFilenamePathname = logFileStoragePath + "/"
                    + Util.ISLogFilenamePath.replace(".txt", "")
                    + "_" + dateTimeToStringFormat(LocalDateTime.now())
                    + ".txt";
            // Create log dir if not exists
            (new File(logFileStoragePath)).mkdir();
            // Rename and move existing logfile
            f.renameTo(new File(logNewFilenamePathname));

            // Manage storage logs duration backup
            if ((new File(logFileStoragePath).exists())) {
//                // Parcour tous les fichiers de mon répertoire 
//                boucleSurChaqueFichierDuReperoire
//                {
//                    // Durée d'existance
//                    if ((AcutalTime - logFileBackupTime) > logFileDurationJ && logFileDurationJ > 0) {
//                        // Je supprime tous les fichier supérieure à logFileDurationJ
//                    }
//                }
//
            }
        }
    }

    /**
     * Convert a local date time to a string formated as YYYYMMDD_HHMMSS
     *
     * @param dt is the date time to be converted
     * @return a converted value of dt to string date time YYYYMMDD_HHMMSS
     */
    public static String dateTimeToStringFormat(LocalDateTime dt) {
        return ""
                + dt.getYear()
                + (dt.getMonthValue() < 10 ? "0" + dt.getMonthValue() : dt.getMonthValue())
                + (dt.getDayOfMonth() < 10 ? "0" + dt.getDayOfMonth() : dt.getDayOfMonth())
                + "_"
                + (dt.getHour() < 10 ? "0" + dt.getHour() : dt.getHour())
                + (dt.getMinute() < 10 ? "0" + dt.getMinute() : dt.getMinute())
                + (dt.getSecond() < 10 ? "0" + dt.getSecond() : dt.getSecond());
    }

}

//        if (logMaxSizeMB == 0) {
//            Util.out("Pas d'enregistrement permis logMaxSizeMB = 0 ");
//        } else {
//            File f = new File(Util.ISLogFilenamePath);
//            if (f.length() >= logMaxSizeMB * 1000000) {
//                Util.out("Taille du fichier dépasse la limite de " + logMaxSizeMB * 1000000 + " octet");
//            }
//        }
