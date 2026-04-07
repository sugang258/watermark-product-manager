import { useEffect, useState } from "react";

function App() {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [stockQuantity, setStockQuantity] = useState("");
  const [images, setImages] = useState([]);
  const [items, setItems] = useState([]);

  const fetchItems = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/items");
      if (!res.ok) {
        throw new Error("상품 조회 실패");
      }
      const data = await res.json();
      setItems(data);
    } catch (error) {
      console.error(error);
      alert("상품 목록을 불러오지 못했어요.");
    }
  };

  useEffect(() => {
    fetchItems();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!name || !price || !stockQuantity) {
      alert("상품명, 가격, 재고를 입력해줘.");
      return;
    }

    if (images.length === 0) {
      alert("이미지를 1개 이상 선택해줘.");
      return;
    }

    const item = {
      name,
      price: Number(price),
      stockQuantity: Number(stockQuantity),
    };

    const formData = new FormData();
    formData.append(
        "item",
        new Blob([JSON.stringify(item)], { type: "application/json" })
    );

    for (let i = 0; i < images.length; i++) {
      formData.append("images", images[i]);
    }

    try {
      const res = await fetch("http://localhost:8080/api/items", {
        method: "POST",
        body: formData,
      });

      if (!res.ok) {
        throw new Error("상품 등록 실패");
      }

      alert("상품 등록 완료!");

      setName("");
      setPrice("");
      setStockQuantity("");
      setImages([]);

      fetchItems();
    } catch (error) {
      console.error(error);
      alert("상품 등록 중 오류가 발생했어요.");
    }
  };

  return (
      <div style={{ padding: "30px", maxWidth: "1000px", margin: "0 auto" }}>
        <h1>상품 등록</h1>

        <form onSubmit={handleSubmit} style={{ marginBottom: "40px" }}>
          <div style={{ marginBottom: "12px" }}>
            <label>상품명 </label>
            <input
                value={name}
                onChange={(e) => setName(e.target.value)}
                style={{ marginLeft: "10px", padding: "6px", width: "300px" }}
            />
          </div>

          <div style={{ marginBottom: "12px" }}>
            <label>가격 </label>
            <input
                type="number"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                style={{ marginLeft: "24px", padding: "6px", width: "300px" }}
            />
          </div>

          <div style={{ marginBottom: "12px" }}>
            <label>재고 </label>
            <input
                type="number"
                value={stockQuantity}
                onChange={(e) => setStockQuantity(e.target.value)}
                style={{ marginLeft: "24px", padding: "6px", width: "300px" }}
            />
          </div>

          <div style={{ marginBottom: "12px" }}>
            <label>이미지 </label>
            <input
                type="file"
                multiple
                accept="image/*"
                onChange={(e) => setImages(Array.from(e.target.files))}
                style={{ marginLeft: "10px" }}
            />
          </div>

          <p>첫 번째 이미지가 썸네일로 저장돼.</p>

          <button type="submit" style={{ padding: "10px 16px" }}>
            상품 등록
          </button>
        </form>

        <hr />

        <h2 style={{ marginTop: "30px" }}>상품 목록</h2>

        {items.length === 0 ? (
            <p>등록된 상품이 없어요.</p>
        ) : (
            <div style={{ display: "grid", gap: "20px" }}>
              {items.map((item) => {
                const thumbnail = item.images.find((img) => img.thumbnail);

                return (
                    <div
                        key={item.id}
                        style={{
                          border: "1px solid #ddd",
                          borderRadius: "10px",
                          padding: "16px",
                        }}
                    >
                      <h3>{item.name}</h3>
                      <p>가격: {item.price}원</p>
                      <p>재고: {item.stockQuantity}개</p>

                      {thumbnail && (
                          <img
                              src={`http://localhost:8080${thumbnail.imageUrl}`}
                              alt={item.name}
                              style={{ width: "200px", marginTop: "10px" }}
                          />
                      )}
                    </div>
                );
              })}
            </div>
        )}
      </div>
  );
}

export default App;