package gt.tribal.app.domain.repository

import gt.tribal.app.domain.source.PhotoLocalDataSource
import gt.tribal.app.ui.PhotoEntity

class PhotoLocalRepository(
    private val photoLocalDataSource: PhotoLocalDataSource
) {

    suspend fun savePhotoFavorite(photo: PhotoEntity){
        photoLocalDataSource.savePhotoFavorite(photo)
    }

    suspend fun getPhotos(): List<PhotoEntity>{
        return photoLocalDataSource.getPhotos()
    }

    suspend fun deletePhoto(id: String){
        photoLocalDataSource.deletePhoto(id)
    }
}