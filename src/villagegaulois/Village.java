package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbetals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbetals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit)
	{
		int i;
		i=marche.trouverEtalLibre();
		marche.utiliserEtal(0, vendeur, produit, nbProduit);
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom()+" cherche un endroit pour vendre"+" "+nbProduit +" "+ produit);
		chaine.append("le vendeur "+vendeur.getNom()+"vend des "+produit+marche.trouverVendeur(vendeur));
		return chaine.toString();
	}

	private static class Marche {
		private Etal[] etals;

		private Marche(int nbEtals) {
			this.etals = new Etal[100];
			
		}

		void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			
			if(etals[indiceEtal].isEtalOccupe()==false)
			{
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}

		int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}

			}

			return -1;
		}

		Etal[] trouverEtals(String produit) {
			Etal[] temp = new Etal[etals.length];
			int x = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					temp[x] = etals[i];
					x++;
				}
			}
			return temp;
		}

		Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}

			}
			return null;
		}

		String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
			}
			int nbEtalslibre = 0;
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					nbEtalslibre++;
				}
			}
			if (nbEtalslibre != 0) {
				chaine.append("Il reste" + nbEtalslibre + "etals non utilisés dans le marché.\n");
			}
			return chaine.toString();
		}

	}
}