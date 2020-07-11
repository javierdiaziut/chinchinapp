package gt.tribal.app.domain.source

import gt.tribal.app.data.database.PhotoDao
import gt.tribal.app.ui.PhotoEntity

class PhotoLocalDataSource(
    private val photoDao: PhotoDao
) {

    suspend fun savePhotoFavorite(photo: PhotoEntity){
        photoDao.save(photo)
    }

    suspend fun getPhotos(): List<PhotoEntity>{
        return photoDao.get(true)
    }

    suspend fun deletePhoto(id: String){
        photoDao.deletePhoto(id)
    }
}