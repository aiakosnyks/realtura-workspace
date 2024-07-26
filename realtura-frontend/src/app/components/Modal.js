
const Modal = ({ onClose, onCreateListing }) => {
    let title = [];
    let description = [];

    const handleSubmit = (e) => {
        e.preventDefault();
        if (title && description) {
            onCreateListing({ title, description, active: true });
        }
    };

    return (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-50">
            <div className="w-full max-w-lg p-6 bg-white rounded shadow-lg">
                <h2 className="mb-4 text-2xl font-bold">Yeni İlan Oluştur</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block mb-2">Başlık</label>
                        <input
                            type="text"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                            className="w-full px-3 py-2 border rounded"
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block mb-2">Açıklama</label>
                        <textarea
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                            className="w-full px-3 py-2 border rounded"
                        />
                    </div>
                    <div className="flex justify-end">
                        <button
                            type="button"
                            onClick={onClose}
                            className="px-4 py-2 mr-2 text-gray-600 bg-gray-200 rounded"
                        >
                            İptal
                        </button>
                        <button type="submit" className="px-4 py-2 text-white bg-blue-500 rounded">
                            Oluştur
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Modal;
