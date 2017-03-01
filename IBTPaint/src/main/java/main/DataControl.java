package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import engine.ContextGraphics;
import figures.utils.IShape;
import figures.utils.impl.Line;
import figures.utils.impl.Point;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

public class DataControl {
	private static File saveFile;

	public static void save(ContextGraphics graphics) {
		// prepare a service record
		Line line = new Line("service", new Point(0, 0), new Point(0, 0));
		double[] canvasSize = new double[2];
		canvasSize[0] = graphics.getMainWidth();
		canvasSize[1] = graphics.getMainHeight();
		line.setDashes(canvasSize);
		List<IShape> saveShapes = new ArrayList<IShape>();
		saveShapes = graphics.getSheet().getShapes();
		saveShapes.add(line);

		saveFile = getSaveFile();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
			oos.writeObject(saveShapes);
		} catch (Exception ex) {
			System.out.println("Exception: Message = " + ex.getMessage());
			ex.printStackTrace();
		}
		new Alert(Alert.AlertType.INFORMATION, saveFile.getName() + " is saved successfully.").show();
	}

	public static List<IShape> load() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(getOpenFile());
			ObjectInputStream oin;
			try {
				oin = new ObjectInputStream(fis);
				List<IShape> shapes;
				try {
					shapes = (List<IShape>) oin.readObject();
					System.out.println("readObject is true: List<IShape> size=" + shapes.size());
					oin.close();
					return shapes;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}

	private static File getSaveFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save As..");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Paint File(.PNT)", "*.pnt"));
		return fileChooser.showSaveDialog(Main.getInstance().getPrimaryStage());
	}

	private static File getOpenFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Paint File(.PNT)", "*.pnt"));
		return fileChooser.showOpenDialog(Main.getInstance().getPrimaryStage());
	}

}
