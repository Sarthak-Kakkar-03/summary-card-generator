package cs3500.pa01.filetraverse;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;


/**
 * Used to find the relevant files in the given directory
 */
public class FileFinder implements FileVisitor<Path> {

  /**
   * The path whose directory and subdirectories we want to access
   */
  public Path targetInputDirectory;
  private final ArrayList<Path> directoryFiles;
  private boolean visitCallFlag = false;

  public FileFinder(String targetInputDirectory) {
    this.targetInputDirectory = Paths.get(targetInputDirectory);
    this.directoryFiles = new ArrayList<>();
  }


  /**
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return the visit result
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return FileVisitResult.CONTINUE;
  }

  /**
   * Invoked for a file in a directory.
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return the visit result
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    this.visitCallFlag = true;
    if (this.isAncestor(file) && file.toString().endsWith(".md")) {
      directoryFiles.add(file);
    }
    return FileVisitResult.CONTINUE;
  }

  /**
   * Invoked for a file that could not be visited. This method is invoked
   * if the file's attributes could not be read, the file is a directory
   * that could not be opened, and other reasons.
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return the visit result
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    System.err.println("Couldn't access the " + file + "because: " + exc);
    return FileVisitResult.CONTINUE;
  }

  /**
   * Invoked for a directory after entries in the directory, and all of their
   * descendants, have been visited. This method is also invoked when iteration
   * of the directory completes prematurely (by a {@link #visitFile visitFile}
   * method returning {@link FileVisitResult#SKIP_SIBLINGS SKIP_SIBLINGS},
   * or an I/O error when iterating over the directory).
   *
   * @return the visit result
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return FileVisitResult.CONTINUE;
  }

  /**
   * @return The post walk array List of Paths
   */
  public ArrayList<Path> getDirectoryFiles() {
    if (this.visitCallFlag) {
      return this.directoryFiles;
    } else {
      throw new IllegalStateException("The Array for the input directory hasn't been walked yet "
          + "please call the Files.walkFileTree method first"
      );
    }

  }

  /**
   * @param file is the path we are checking existence within the directory for
   * @return a boolean checking if this file is a subdirectory of the Target Input directory
   */
  private boolean isAncestor(Path file) {
    return
        file.getParent().toAbsolutePath().startsWith(this.targetInputDirectory.toAbsolutePath());
  }

}
