package com.editor.core.persistence;

import java.io.*;

/**
 * Utilitaire pour sauvegarder et charger des objets via sérialisation.
 */
public class SaveManager {

    /**
     * Sauvegarde un objet sérialisable dans un fichier.
     * @param path chemin du fichier
     * @param obj objet à sauvegarder
     * @throws IOException en cas d'erreur d'écriture
     */
    public static void save(String path, Object obj) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(obj);
        }
    }

    /**
     * Charge un objet depuis un fichier sérialisé.
     * @param path chemin du fichier
     * @return l'objet restauré
     * @throws IOException en cas d'erreur d'ouverture
     * @throws ClassNotFoundException si la classe de l'objet est inconnue
     */
    public static Object load(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return in.readObject();
        }
    }
}
