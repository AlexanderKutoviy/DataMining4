package com.da31.kutoviy;

import weka.classifiers.trees.REPTree;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
//        String PATH_TRAINING = "D://temp/bmw-training.arff";
//        String PATH_TEST = "D://temp/bmw-test.arff";
//        ArffLoader loader = new ArffLoader();
//
//        loader.setFile(new File(PATH_TRAINING));
//        Instances structure = loader.getDataSet();
//        structure.setClassIndex(structure.numAttributes() - 4);
//        REPTree lr = new REPTree();
//        lr.buildClassifier(structure);
//        System.out.println(lr);
//        loader.setFile(new File(PATH_TEST));
//        Instances structure1 = loader.getDataSet();
//        structure1.setClassIndex(structure.numAttributes() - 4);
//        int correctclass = 0;
//        for (int i = 0; i < structure1.size(); i++) {
//            Instance current = structure1.get(i);
//            double currentclass = lr.classifyInstance(current);
//            if (currentclass == current.value(0))
//                correctclass++;
//        }
//        System.out.println(correctclass + "/" + structure1.size() + ":" + correctclass * 100 / structure1.size() + "%");
        claster();

    }

    public static void claster() {
        try {
            SimpleKMeans kmeans = new SimpleKMeans();
            kmeans.setNumClusters(7);
            String PATH_TRAINING = "D://temp/bmw-training.arff";
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(PATH_TRAINING));
            Instances data = loader.getDataSet();
            kmeans.buildClusterer(data);
            System.out.println(kmeans.toString());
            int correctclust = 0;
            for (int i = 0; i < data.size(); i++) {
                int attribute = 3;
                if (data.get(i).value(attribute) == kmeans.getClusterCentroids().get(kmeans.clusterInstance(data.get(i))).value(attribute))
                    correctclust++;
            } System.out.println(correctclust + "/" + data.size() + ":" + correctclust * 100 / data.size() + " % ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
